package co.louga.domain.rx

import io.reactivex.Scheduler

open class ExecutionScheduler(val executionScheduler: Scheduler, val postExecutionScheduler: Scheduler)