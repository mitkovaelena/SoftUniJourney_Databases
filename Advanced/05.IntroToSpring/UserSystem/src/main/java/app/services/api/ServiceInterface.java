package app.services.api;

public interface ServiceInterface<E> {

    E findByID(Long id);

    void remove(E object);

    Iterable<E> findAll();

    E save(E object);
}