package id.co.bca.intra.traning.microservicesRest.service;

import id.co.bca.intra.traning.microservicesRest.entity.BaseResponse;
import id.co.bca.intra.traning.microservicesRest.entity.Book;
import id.co.bca.intra.traning.microservicesRest.repository.BookRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BaseResponse<Iterable<Book>> getAll(){
        BaseResponse<Iterable<Book>> data = new BaseResponse<>();
        data.setData(bookRepository.findAll());
        return data;
    }

    public BaseResponse<Book> findById(Long id){
        BaseResponse<Book> result = new BaseResponse<>();
        Book data = bookRepository.findById(id).orElse(null);
        if (data == null) {
            result.setMessage("Data not Found");
            result.setStatusCode(HttpStatus.NOT_FOUND.value());
        }
        result.setData(data);
        return result;
    }

    public BaseResponse<Book> insertBook(Book newBook){
        int httpCode = HttpStatus.CREATED.value();
        String message = "Success";
        Book exist = bookRepository.findById(newBook.getId()).orElse(null);
        BaseResponse<Book> result = new BaseResponse<>();
        if (!validateBook(newBook) || exist != null) {
            httpCode = HttpStatus.BAD_REQUEST.value();
            newBook = null;
            if (exist != null) message = "Book already exists";
            else message = "Failed validation";
        }
        result.setMessage(message);
        result.setStatusCode(httpCode);
        result.setData(newBook);
        if (httpCode == HttpStatus.CREATED.value()) bookRepository.save(newBook);
        return result;
    }

    public BaseResponse<Book> updateBook(Book updateBook, Long id){
        int httpCode = HttpStatus.OK.value();
        String message = "Success";
        BaseResponse<Book> result = new BaseResponse<>();
        Book book = bookRepository.findById(id).orElse(null);
        updateBook.setId(id);
        if (book == null) {
            httpCode = HttpStatus.NOT_FOUND.value();
            message = "Data not Found";
        }
        if (!validateBook(updateBook)){
            httpCode = HttpStatus.BAD_REQUEST.value();
            message = "Failed validation";
        }
        result.setMessage(message);
        result.setStatusCode(httpCode);
        result.setData(updateBook);
        if (httpCode == HttpStatus.OK.value()) bookRepository.save(updateBook);
        return result;
    }

    public BaseResponse<Book> deleteBook(Long id){
        Book book = bookRepository.findById(id).orElse(null);
        BaseResponse<Book> result = new BaseResponse<>();
        result.setData(null);
        if (book == null) {
            result.setMessage("Not Found");
            result.setStatusCode(404);
        }
        bookRepository.deleteById(id);
        return result;
    }

    private boolean validateBook(Book book){
        if (StringUtils.isEmpty(book.getJudul()) || StringUtils.isEmpty(book.getPenulis()) || book.getTahunTerbit() <= 0) return false;
        return true;
    }
}
