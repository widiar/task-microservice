package id.co.bca.intra.traning.microservicesRest.controller;

import id.co.bca.intra.traning.microservicesRest.entity.BaseResponse;
import id.co.bca.intra.traning.microservicesRest.entity.Book;
import id.co.bca.intra.traning.microservicesRest.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buku")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<Iterable<Book>> allBook(){
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponse<Book>> getById(@PathVariable(name = "id") Long id){
        BaseResponse<Book> result = bookService.findById(id);
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getStatusCode()));
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse<Book>> addBook(@RequestBody Book newBook){
        BaseResponse<Book> result = bookService.insertBook(newBook);
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getStatusCode()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Book>> updateBook(@PathVariable(name = "id") Long id, @RequestBody Book updateBook){
        BaseResponse<Book> result = bookService.updateBook(updateBook, id);
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getStatusCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Book>> deleteBook(@PathVariable(name = "id") Long id){
        BaseResponse<Book> result = bookService.deleteBook(id);
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getStatusCode()));
    }
}
