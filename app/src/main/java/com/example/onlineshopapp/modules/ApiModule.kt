package com.example.onlineshopapp.modules

import com.example.onlineshopapp.api.cusomer.UsersApi
import com.example.onlineshopapp.api.invoice.InvoiceApi
import com.example.onlineshopapp.api.invoice.TransactionApi
import com.example.onlineshopapp.api.product.ProductApi
import com.example.onlineshopapp.api.product.ProductCategoryApi
import com.example.onlineshopapp.api.product.ProductColorApi
import com.example.onlineshopapp.api.site.BlogApi
import com.example.onlineshopapp.api.site.ContentApi
import com.example.onlineshopapp.api.site.SliderApi
import com.example.onlineshopapp.config.UnsafeSSLConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideApi(): Retrofit {

        return  Retrofit.Builder()
            .baseUrl("https://onlineshop.holosen.net:9090")
            .client(UnsafeSSLConfig.unsafeOkHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

//https://onlineshop.holosen.net:9090/
//https://10.0.2.2:8080
        }




    @Provides
    @Singleton
    fun provideUserApi(): UsersApi {
        return provideApi().create(UsersApi::class.java)
    }

    @Provides
    @Singleton
    fun provideInvoiceApi(): InvoiceApi {
        return provideApi().create(InvoiceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTransactionApi(): TransactionApi {
        return provideApi().create(TransactionApi::class.java)
    }

    @Provides
    @Singleton
    fun provideColorApi(): ProductColorApi {
        return provideApi().create(ProductColorApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductApi(): ProductApi {
        return provideApi().create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductCategoryApi(): ProductCategoryApi {
        return provideApi().create(ProductCategoryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBlogApi(): BlogApi {
        return provideApi().create(BlogApi::class.java)
    }

    @Provides
    @Singleton
    fun provideContentApi(): ContentApi {
        return provideApi().create(ContentApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSliderApi(): SliderApi {
        return provideApi().create(SliderApi::class.java)
    }
}