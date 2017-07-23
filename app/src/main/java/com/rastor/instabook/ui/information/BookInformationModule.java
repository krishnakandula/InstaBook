package com.rastor.instabook.ui.information;

import com.rastor.instabook.data.books.source.BookRepository;
import com.rastor.instabook.util.FragmentScoped;

import dagger.Module;
import dagger.Provides;
import lombok.NonNull;

/**
 * Created by Krishna Chaitanya Kandula on 7/8/17.
 */

@Module
public class BookInformationModule {

    private final String bookId;

    private final BookInformationContract.View view;

    public BookInformationModule(@NonNull String bookId, @NonNull BookInformationContract.View view) {
        this.bookId = bookId;
        this.view = view;
    }

    @Provides
    @FragmentScoped
    public BookInformationContract.View provideBookInformationView() {
        return this.view;
    }

    @Provides
    @FragmentScoped
    public BookInformationContract.Presenter provideBookInformationContractPresenter(BookInformationPresenter presenter) {
        return presenter;
    }

    @Provides
    @FragmentScoped
    public BookInformationPresenter provideBookInformationPresenter(@NonNull BookRepository booksRepository) {
        return new BookInformationPresenter(this.bookId, this.view, booksRepository);
    }
}
