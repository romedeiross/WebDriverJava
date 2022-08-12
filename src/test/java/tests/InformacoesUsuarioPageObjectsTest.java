package tests;
import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import suporte.Web;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioPageObjectsTest.csv" )
public class InformacoesUsuarioPageObjectsTest {
    private WebDriver navegador;

    @Before
    public void setUp() {
        navegador = Web.creatChrome();
    }

    @Test
    public void testaAdicionarUmaInformaçãoAdicionalDoUsuario(@Param(name = "login")String login,
                                                              @Param(name = "senha")String senha,
                                                              @Param(name = "tipo")String tipo,
                                                              @Param(name = "contato")String contato,
                                                              @Param(name = "mensagem")String mensagemEsperada
    ){
        String textoToast = new LoginPage(navegador)
                .clicarSigIn()
                .fazerLogin( login, senha)
                .clicarMe()
                .clicarAbaMoreDataAboutYou()
                .clicarBotaoAddMoreDataAboutYou()
                .adicionarContato(tipo, contato)
                .capturarTextoToast();

        Assert.assertEquals( mensagemEsperada, textoToast);
    }

    @After
    public void tearDown() {
        //navegador.quit();
    }
}
