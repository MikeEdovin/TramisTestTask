package Repositories;

import Entities.Declaration;
import org.springframework.data.repository.CrudRepository;

public interface DeclarationRepository extends CrudRepository<Declaration,Integer> {
    Declaration findByDeclarationNumber(String declarationNumber);
}
