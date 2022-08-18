package pedido;

import Armazem.Armazem;
import ingredientes.Fruta;
import ingredientes.TipoFruta;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArmazemTest {

    Armazem armazem;
    Fruta morango;
    Fruta abacate;
    Fruta banana;
    private final String INGREDIENTE_NAO_ENCONTRADO = "Ingrediente não encontrado";
    private final String INGREDIENTE_JA_CADASTRADO = "Ingrediente já cadastrado.";
    private final String QUANTIDADE_INVALIDA = "Quantidade Inválida";

    @BeforeAll
    void setup() {
        armazem = new Armazem();
        morango = new Fruta(TipoFruta.MORANGO);
        abacate = new Fruta(TipoFruta.ABACATE);
        banana = new Fruta(TipoFruta.BANANA);
    }

    @BeforeEach
    void setupEach() {
        armazem = new Armazem();
    }

    @Test
    @DisplayName("Deve registrar os ingredientes, quando não forem repetidos")
    void ShouldRegisterIngredients_WhenDoNotRepeatIngredients() {
        armazem.cadastrarIngredienteEmEstoque(morango);
        armazem.cadastrarIngredienteEmEstoque(abacate);
        armazem.cadastrarIngredienteEmEstoque(banana);

        assertAll(
                () -> assertEquals(0, armazem.consultarQuantidadeDoIngredienteEmEstoque(morango)),
                () -> assertEquals(0, armazem.consultarQuantidadeDoIngredienteEmEstoque(banana)),
                () -> assertEquals(0, armazem.consultarQuantidadeDoIngredienteEmEstoque(abacate))
        );

    }

    @Test
    @DisplayName("Não Deve registrar os ingredientes, se já existe")
    void ShouldNotRegisterIngredients_WhenRepeatedIngredients() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            armazem.cadastrarIngredienteEmEstoque(morango);
            armazem.cadastrarIngredienteEmEstoque(morango);
        });

        assertEquals(INGREDIENTE_JA_CADASTRADO, exception.getMessage());
    }

    @Test
    @DisplayName("Não deve adicionar ingredientes, quando abaixo de zero")
    void ShouldNotAddIngredients_WhenAddedValueIsBelowZero() {
        armazem.cadastrarIngredienteEmEstoque(morango);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            armazem.adicionarQuantidadeDoIngredienteEmEstoque(morango, -10);
        });

        assertEquals(QUANTIDADE_INVALIDA, exception.getMessage());
    }

    @Test
    @DisplayName("Deve descadastrar ingredientes que existem")
    void ShouldUnregister_WhenIngredientAlreadyExists() {
        armazem.cadastrarIngredienteEmEstoque(morango);
        armazem.descadastrarIngredienteEmEstoque(morango);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            armazem.consultarQuantidadeDoIngredienteEmEstoque(morango);
        });

        assertEquals(INGREDIENTE_NAO_ENCONTRADO, exception.getMessage());
    }

    @Test
    @DisplayName("Deve adicionar, quando ingrediente está registrado")
    void ShouldAdd_WhenIngredientsIsRegistered() {
        armazem.cadastrarIngredienteEmEstoque(morango);
        armazem.adicionarQuantidadeDoIngredienteEmEstoque(morango, 10);

        assertEquals(10, armazem.consultarQuantidadeDoIngredienteEmEstoque(morango));
    }

    @Test
    @DisplayName("Não deve adicionar, quando ingrediente não está cadastrado")
    void ShouldNotAdd_WhenIngredientIsNotRegistered() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            armazem.adicionarQuantidadeDoIngredienteEmEstoque(morango, 10);
        });

        assertEquals(INGREDIENTE_NAO_ENCONTRADO, exception.getMessage());
    }

    @Test
    @DisplayName("Deve reduzir quantidade quando ingrediente está registrado")
    void ShouldReduce_WhenIngredientIsRegistered() {
        armazem.cadastrarIngredienteEmEstoque(morango);
        armazem.adicionarQuantidadeDoIngredienteEmEstoque(morango, 10);
        armazem.reduzirQuantidadeDoIngredienteEmEstoque(morango, 5);

        assertEquals(5, armazem.consultarQuantidadeDoIngredienteEmEstoque(morango));
    }

    @Test
    @DisplayName("Deve soltar exceção quando tentar descastrar ingrediente que não existe")
    void ShouldThrowException_WhenUnregisterIngredientNotFound() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> armazem.descadastrarIngredienteEmEstoque(morango));

        assertEquals(INGREDIENTE_NAO_ENCONTRADO, exception.getMessage());
    }

    @Test
    @DisplayName("Não deve reduzir quantidade quando menor que zero")
    void ShouldNotReduce_WhenQuantityBelowZero() {
        armazem.cadastrarIngredienteEmEstoque(morango);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            armazem.reduzirQuantidadeDoIngredienteEmEstoque(morango, 10);
        });

        assertEquals(QUANTIDADE_INVALIDA, exception.getMessage());
    }

    @Test
    @DisplayName("Não deve reduzir ingredientes quando maior que a quantidade")
    void ShouldNotReduce_WhenIngredientQuantityBelowReduceQuantity() {
        armazem.cadastrarIngredienteEmEstoque(morango);
        armazem.adicionarQuantidadeDoIngredienteEmEstoque(morango, 5);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            armazem.reduzirQuantidadeDoIngredienteEmEstoque(morango, 10);
        });

        assertEquals(QUANTIDADE_INVALIDA, exception.getMessage());
    }

}
