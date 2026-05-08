package com.example.libraryapi.service;

import com.example.libraryapi.exception.ResourceNotFoundException;
import com.example.libraryapi.model.Author;
import com.example.libraryapi.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long id, Author updatedAuthor) {
        Author existingAuthor = getAuthorById(id);

        existingAuthor.setFirstName(updatedAuthor.getFirstName());
        existingAuthor.setLastName(updatedAuthor.getLastName());
        existingAuthor.setBirthYear(updatedAuthor.getBirthYear());
        existingAuthor.setNationality(updatedAuthor.getNationality());

        return authorRepository.save(existingAuthor);
    }

    public void deleteAuthor(Long id) {
        Author existingAuthor = getAuthorById(id);
        authorRepository.delete(existingAuthor);
    }
}