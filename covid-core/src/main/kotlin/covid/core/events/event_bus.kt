package covid.core.events

import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.*
import io.reactivex.rxjava3.subjects.*
import io.reactivex.rxjava3.schedulers.*
import org.koin.dsl.*

interface Subscription {
  fun unsubscribe()
}

data class EventChannel<T>(
  val type: Class<T>,
  val filter: (T) -> Boolean = { true }
)

data class EventHandler<T>(
  val receiver: (T) -> Unit,
  val scheduler: Scheduler = Schedulers.io(),
  val error: (Throwable) -> Unit = {}
)

interface EventBus {
  fun publish(event: Any)
  
  fun <T> subscribe(channel: EventChannel<T>, handler: EventHandler<T>): Subscription
}

inline fun <reified T: Any> EventBus.subscribe(
    scheduler: Scheduler = Schedulers.io(),
    noinline filter: (T) -> Boolean = { true },
    noinline error: (Throwable) -> Unit = {}, 
    noinline receiver: (T) -> Unit): Subscription {
  val channel = EventChannel(T::class.java, filter)
  val handler = EventHandler(receiver, scheduler, error)
  
  return this.subscribe(channel, handler)
}

class RxSubscription(
  private val disposable: Disposable
): Subscription {
  override fun unsubscribe() {
    disposable.dispose()
  }
}

class RxEventBus : EventBus {
  private val pub: PublishSubject<Any> = PublishSubject.create()
  
  override fun publish(event: Any) {
    if (pub.hasObservers()) {
      pub.onNext(event)
    }
  }
  
  override fun <T> subscribe(channel: EventChannel<T>, handler: EventHandler<T>): Subscription {
    val sub = pub
      .toFlowable(BackpressureStrategy.BUFFER)
      .observeOn(handler.scheduler)
      .ofType(channel.type)
      .filter { event -> channel.filter(event) }
      .subscribe(
        { event ->
          handler.receiver(event)
        },
        { error ->
          handler.error(error)
        }
      )
    
    return RxSubscription(sub)
  }
}