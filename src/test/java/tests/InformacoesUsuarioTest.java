package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Web;

@RunWith(DataDrivenTestRunner.class)
    @DataLoader(filePaths = "InformacoesUsuarioTest.csv" )

public class InformacoesUsuarioTest {
    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp() {
        navegador = Web.creatChrome();

        //Clicar no link que contém o texto "sig in"
        navegador.findElement(By.linkText("Sign in")).click();


        //Identificando o formulário de Login
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));

        //Digitar no campo com name "login" que está dentro do formulário de id "signinbox" o texto "julio0001"
        formularioSignInBox.findElement(By.name("login")).sendKeys("julio0001");

        //Digitar no campo com name "password" que está dentro do formulário de id "signinbox"
        formularioSignInBox.findElement(By.name("password")).sendKeys("123456");

        //Clicar no link com o texto "SIGN IN"
        navegador.findElement(By.linkText("SIGN IN")).click();

        //Clicra em um link que possui a class "me"
        navegador.findElement(By.className("me")).click();

        //Clicar em um link que tenha o texto "MORE DATA ABOUT YOU"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }

    @Test //@Test para que sejam interpretados como um teste válido ao clicar em "executar"
    public void testaAdicionarUmaInformaçãoAdicionalDoUsuario(@Param(name="tipo")String tipo, @Param(name="contato") String contato,@Param(name="mensagem") String mensagemEsperada ) {
        /*(Xpath)
        //(duas barras procura em toda página /(uma barra procure exatamente o primeior filho)
        //[] (parametro)
        //* (qalquer atributo)*/
        //Clicar no botão através do seu Xpath buton[@data-target="addmoredata"
        navegador.findElement(By.xpath("//button[@data-target='addmoredata']")).click();

        //Identificar a popup onde esta o formulário de Id "addmordata"
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        //No combo de name "type" escolher a opção "Phone"
        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);

        // No campo de name "contact" digitar "+5511988888888"
        popupAddMoreData.findElement(By.name("contact")).sendKeys(contato);

        //Clicar no link de text "SAVE" que está na popup
        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        //Na mensagem de id "toast-container" validar que o texto é "your contact has been added!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        Assert.assertEquals(mensagemEsperada, mensagem);
    }
    @Test
    public void removerUmContatoDeUsuario(@Param(name="telefone")String telefone,@Param(name="mensagem") String mensagemEsperada ) {
        //Clica no elemento pelo seu xpath //span[text()="+55115555555555"]/following-sibling::a"
        navegador.findElement(By.xpath("//span[text()=\""+ telefone +"\"]/following-sibling::a")).click();

        //Confirmar a exclusão na janela do JavaScript
        navegador.switchTo().alert().accept();

        //Validar que a mensagem apresentada foi "Rest in peace, dear phone!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        Assert.assertEquals(mensagem, mensagem);

        //String screenShotArquivo = "C:\\Users\\rosal\\OneDrive\\Área de Trabalho\\screenshots" + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
        //Screenshot.tirar(navegador, screenShotArquivo);

        //aguardar até 20 segundos para que a janela JS desapareça
        WebDriverWait aguardar = new WebDriverWait(navegador, 20);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));

        //Clicar no link com o texto "logout"
        navegador.findElement(By.linkText("Logout")).click();
    }

    @After
    public void tearDown() {
       // navegador.quit();

    }
}

