package com.ikimaka.cryptoapp.di

import androidx.work.ListenableWorker
import com.ikimaka.cryptoapp.data.workers.ChildWorkerFactory
import com.ikimaka.cryptoapp.data.workers.RefreshDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    fun bindRefreshDataWorkerFactory(worker: RefreshDataWorker.Factory): ChildWorkerFactory
}