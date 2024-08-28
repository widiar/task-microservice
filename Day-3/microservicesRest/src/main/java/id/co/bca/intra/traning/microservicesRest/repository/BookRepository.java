package id.co.bca.intra.traning.microservicesRest.repository;

import id.co.bca.intra.traning.microservicesRest.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
