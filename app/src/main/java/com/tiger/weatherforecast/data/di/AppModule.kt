package com.tiger.weatherforecast.data.di

import com.tiger.weatherforecast.data.datasource.WeatherRemoteDataSource
import com.tiger.weatherforecast.data.datasource.network.AuthInterceptor
import com.tiger.weatherforecast.repository.WeatherRepository
import com.tiger.weatherforecast.repository.WeatherRepositoryImpl
import com.tiger.weatherforecast.ui.MainViewModel
import com.tiger.weatherforecast.ui.utils.UiModelMapper
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    //Network
    factory { AuthInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideRetrofit(get()) }
    single { provideWeatherDataSource(get()) }

    //Repository
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }

    //Mapper
    factory { UiModelMapper() }

    //ViewModel
    viewModel {
        MainViewModel(get(), get())
    }
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(
        "https://api.openweathermap.org/"
    ).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

private fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
}

private fun provideWeatherDataSource(retrofit: Retrofit): WeatherRemoteDataSource =
    retrofit.create(WeatherRemoteDataSource::class.java)

