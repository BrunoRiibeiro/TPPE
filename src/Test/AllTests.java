package Test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ClienteEspecialTeste.class, ClienteTeste.class, VendaTeste.class, ProdutoTeste.class})
public class AllTests {

}
