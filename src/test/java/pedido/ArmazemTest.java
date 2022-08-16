package pedido;

import Armazem.Armazem;
import ingredientes.Fruta;
import ingredientes.TipoFruta;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArmazemTest {

    Armazem armazem;
    Fruta morango;
    Fruta abacate;
    Fruta banana;

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
    void ShouldRegisterIngredients_WhenDoNotRepeatIngredients() {
        armazem.cadastrarIngredienteEmEstoque(morango);
        armazem.cadastrarIngredienteEmEstoque(abacate);
        armazem.cadastrarIngredienteEmEstoque(banana);

        assertEquals(0, armazem.consultarQuantidadeDoIngredienteEmEstoque(morango));
        assertEquals(0, armazem.consultarQuantidadeDoIngredienteEmEstoque(banana));
        assertEquals(0, armazem.consultarQuantidadeDoIngredienteEmEstoque(abacate));
    }

    @Test
    void ShouldNotRegisterIngredients_WhenRepeatedIngredients() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            armazem.cadastrarIngredienteEmEstoque(morango);
            armazem.cadastrarIngredienteEmEstoque(morango);
        });

        assertEquals("Ingrediente já cadastrado.", exception.getMessage());
    }

    @Test
    void ShouldUnregister_WhenIngredientAlreadyExists() {
        armazem.cadastrarIngredienteEmEstoque(morango);
        armazem.descadastrarIngredienteEmEstoque(morango);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            armazem.consultarQuantidadeDoIngredienteEmEstoque(morango);
        });

        assertEquals("Ingrediente não encontrado", exception.getMessage());
    }

    @Test
    void ShouldAdd_WhenIngredientsIsRegistered() {
        armazem.cadastrarIngredienteEmEstoque(morango);
        armazem.adicionarQuantidadeDoIngredienteEmEstoque(morango, 10);

        assertEquals(10, armazem.consultarQuantidadeDoIngredienteEmEstoque(morango));
    }

    @Test
    void ShouldNotAdd_WhenIngredientIsNotRegistered(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            armazem.adicionarQuantidadeDoIngredienteEmEstoque(morango, 10);
        });

        assertEquals("Ingrediente não encontrado", exception.getMessage());
    }

    @Test
    void ShouldReduce_WhenIngredientIsRegistered() {
        armazem.cadastrarIngredienteEmEstoque(morango);
        armazem.adicionarQuantidadeDoIngredienteEmEstoque(morango, 10);
        armazem.reduzirQuantidadeDoIngredienteEmEstoque(morango, 5);

        assertEquals(5, armazem.consultarQuantidadeDoIngredienteEmEstoque(morango));
    }

}
