package suporte;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class Web {

    public static WebDriver creatChrome() {
        //Abrindo o Navegador (endereço do chromeDrive na maquina)
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Driver\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Abre o navegador maximizado
        navegador.manage().window().maximize();

        //Navegando ate a pagina do Taskit
        navegador.get("http://www.juliodelima.com.br/taskit");

        return navegador;
    }
}
