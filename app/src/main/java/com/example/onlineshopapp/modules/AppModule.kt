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
import com.example.onlineshopapp.repositories.Transaction.TransactionRepository
import com.example.onlineshopapp.repositories.customer.UserRepository
import com.example.onlineshopapp.repositories.invoice.InvoiceRepository
import com.example.onlineshopapp.repositories.product.ProductCategoryRepository
import com.example.onlineshopapp.repositories.product.ProductColorRepository
import com.example.onlineshopapp.repositories.product.ProductRepository
import com.example.onlineshopapp.repositories.site.BlogRepository
import com.example.onlineshopapp.repositories.site.ContentRepository
import com.example.onlineshopapp.repositories.site.SliderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserRepository(api: UsersApi) = UserRepository(api)

    @Provides
    @Singleton
    fun provideInvoiceRepository(api: InvoiceApi) = InvoiceRepository(api)

    @Provides
    @Singleton
    fun provideTransactionRepository(api: TransactionApi) = TransactionRepository(api)

    @Provides
    @Singleton
    fun provideColorRepository(api: ProductColorApi) =  ProductColorRepository(api)

    @Provides
    @Singleton
    fun provideProductCategoryRepository(api: ProductCategoryApi) = ProductCategoryRepository(api)

    @Provides
    @Singleton
    fun provideProductRepository(api: ProductApi) = ProductRepository(api)

    @Provides
    @Singleton
    fun provideSliderRepository(api: SliderApi) = SliderRepository(api)

    @Provides
    @Singleton
    fun provideBlogRepository(api: BlogApi) = BlogRepository(api)

    @Provides
    @Singleton
    fun provideContentRepository(api: ContentApi) = ContentRepository(api)

}