package com.zara.company;

import org.junit.jupiter.api.Test;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
public class BasicHexagonalTest {

    @Test
    void basicTest() {
        final var hexagon = new ClassFileImporter().importPackages("com.zara");
        layeredArchitecture()
                // Se definen las capas basicas del hexagono.
                .layer("Domain").definedBy("..domain..")

                .layer("UseCases").definedBy("..application.ports.in..")
                .layer("Ports").definedBy("..application.ports.out..")
                .layer("Web").definedBy("..infrastructure.adapters.in..")
                .layer("Services").definedBy("..application.services..")
                .layer("Adapters").definedBy("..infrastructure.adapters.out..")

                // Constraints:
                // La capa Web no puede ser accedida por ninguna otra capa del Hexagono.
                .whereLayer("Web").mayNotBeAccessedByAnyLayer()

                // La capa de UseCases es utilizada por la Web y es implementada por la capa Service.
                .whereLayer("UseCases").mayOnlyBeAccessedByLayers("Web", "Services")

                // La capa de Ports es utilizada por Services y es implementada por la capa Adapters.
                .whereLayer("Ports").mayOnlyBeAccessedByLayers("Services", "Adapters")

                // Un service solo puede ser accedido por otro service.
                // Recordar que la capa Web no accede al service de forma directa,
                // sino que se inyecta por medio de un UseCase.
                .whereLayer("Services").mayOnlyBeAccessedByLayers("Services")
                .check(hexagon);
    }
}
