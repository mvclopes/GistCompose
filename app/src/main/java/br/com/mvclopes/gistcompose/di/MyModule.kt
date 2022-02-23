package br.com.mvclopes.gistcompose.di

import br.com.mvclopes.gistcompose.model.repository.Repository
import br.com.mvclopes.gistcompose.model.repository.RepositoryImp
import br.com.mvclopes.gistcompose.model.repository.api.ApiModule
import br.com.mvclopes.gistcompose.usecases.GetGistsUseCase
import br.com.mvclopes.gistcompose.viewModel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val myModule = module {
    single { ApiModule.apiNetwork }
    factory<Repository> {
        RepositoryImp(
            service = get(),
            dispatcher = Dispatchers.IO
        )
    }
    factory { GetGistsUseCase(repository = get()) }
    factory { HomeViewModel(useCase = get()) }
}