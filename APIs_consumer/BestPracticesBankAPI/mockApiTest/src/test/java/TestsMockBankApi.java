
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class TestsMockBankApi extends apiBase{

    @Test
    public void limpiarDatosEndpoint() {

        // Realizar solicitud GET al endpoint "/users"
        Response response = getRequest("/users");

        //Obteniendo la lista de usuarios como mapas
        List<Map<String, Object>> users = response.jsonPath().getList("");
        //Verificando que user no esté vacío
        if (!users.isEmpty()) {
            for (Map<String, Object> user : users) {
                // Realizando solicitud DELETE para cada usuario usando su ID
                deleteRequest("users/" + user.get("id"));
            }
        }

        //Verificando que el endpoint se encuentre totalmente limpio
        response = getRequest("/users");

        //Aserción de endpoint vacio
        Assert.assertTrue(response.jsonPath().getList("").isEmpty());
        System.out.println("Endpoint luego de limpiarlo: " + response.asString());
    }

    @Test
    public void pruebaCrearUsuarios() {
        // Instanciamos la clase Faker para implementar datos
        Faker faker = new Faker();

        // Creamos un Set para evitar correos repetidos
        Set<String> correos = new HashSet<>();

        User user = new User();
        Response response = null;
        //Creación de 10 usuarios a través de bucle for
        for (int i = 1; i <= 10; i++) {
            // Variable para crear email
            String email;

            // Condicional de creación mientras el email NO este contenido dentro del Set
            do {
                email = faker.internet().emailAddress();
            } while (correos.contains(email));

            // Agregado de correo nuevo al Set
            correos.add(email);

            // Creación de objetos usuario y cuenta;

            Map<String, Object> account = new HashMap<>();

            // Agregado de valores correspondientes al objeto cuenta
            account.put("accountNumber", faker.number().randomNumber());
            Double randomMoney = faker.number().randomDouble(2, 0, 999999);
            BigDecimal bd = new BigDecimal(randomMoney);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            account.put("money", randomMoney);

            // Agregado de valores correspondientes al objeto usuario
            user.setName(faker.name().fullName());
            user.setPassword(faker.internet().password());
            user.setEmail(email);
            user.setId(i);
            user.setAccount(account);

            //Agregando el nuevo Usuario al endpoint
            response = postRequest("/users", user);

        }
        // Solicitud nuevamente para verificar la creación de los 10 usuarios
        response = getRequest("/users");

        //Solicitud y asercion de endpoint "1"
        response = getRequest("/users/1");


        //Impresion por consola de Usuario 1
        System.out.println( "Creacion de usuarios: " +
                "Impresion de User 1: " + response.asString());

    }

    @Test
    public void verificandoDuplicaciónDeCorreos() {
        //Consulta al endpoint usuarios "/users"
        Response response = getRequest("/users");

        //Obtenciion de valores de emails en una Lista
        List<String> correos = response.jsonPath().getList("email");

        //Variable Set para agregar correos
        Set<String> emailSet = new HashSet<>();

        // Iteración del listado
        for (String correo : correos) {

            //Condicional si la clave no contiene el elemento nulo
            if (correo != null) {
                // Condicional de avisio si el correo no se puede agregar dentro del Set.
                if (!emailSet.add(correo)) {
                    System.out.println("Correo duplicado" + correo);
                }
            }
        }
        //Aserción de mismo tamaño de las variables
        Assert.assertEquals(emailSet.size(), correos.size());

    }
    @Test
    public void actualizandoNumeroDeCuenta() {
        //Consulta al endpoint usuario de Id = 5 "users/5"
        Response response = getRequest("users/5");
        //Obtención del objeto del usuario
        Map<String, Object> user = response.jsonPath().getMap("");
        //Obtención del objeto de su cuenta
        Map<String, Object> account = (Map<String, Object>) user.get("account");
        //Agregado de nuevo número de cuenta.
        account.put("accountNumber", 987654321);

        //Envío del nuevo valor por medio del método PATCH.
        response = patchRequest("users/5", user);

        // Obtención del usuario nuevamente
        Response responseUser = getRequest("users/5");
        //Assercion de respuesta de codigo de estado
        Assert.assertEquals(response.getStatusCode(), 200);
        // Obtencion del número de la cuenta del objeto
        Integer accountNumberUser5 = responseUser.jsonPath().get("account.accountNumber");

        System.out.println("Actualización de Número de Cuenta usuario 5 : \nValor anterior: "+  account +
                "\nNuevo valor: " + responseUser.jsonPath().get("account"));



    }
@Test
    public void verificaciónDeDeposito() {
        //Consulta al endpoint usuario de Id = 6 "users/6"
        Response userJsonResponse6 = getRequest("/users/6");

        //Obtención del objeto del usuario
        Map<String, Object> user = userJsonResponse6.jsonPath().getMap("");
        //Obtención del objeto de su cuenta
        Map<String, Object> accountMoney = (Map<String, Object>) user.get("account");

        //Obtención del valor de dinero actual
        BigDecimal bdDineroActual = new BigDecimal(userJsonResponse6.jsonPath().getDouble("account.money"));
        System.out.println("Valores de dinero user 6 antes de depositar " + bdDineroActual);
        BigDecimal montoDeposito = new BigDecimal(320.00);

        bdDineroActual = bdDineroActual.add(montoDeposito);//Suma del Depósito

        BigDecimal loadOfMoney = bdDineroActual.setScale(2, RoundingMode.HALF_UP);//Redodeo del  valor

        accountMoney.put("money", loadOfMoney.doubleValue());//Agregado de nuevo valor al objeto account

        //Envío a través de método PATCH
        userJsonResponse6 = patchRequest("users/6", user);

        // Obtención del usuario nuevamente
        userJsonResponse6 = getRequest("users/6");

        //Obtencion del objeto despues del cambio
        Double moneyUpdate= userJsonResponse6.jsonPath().getDouble("account.money");
        //Conversion y resdondeo del elemento
        BigDecimal bdMoneyUpdate = BigDecimal.valueOf(moneyUpdate).setScale(2, RoundingMode.HALF_UP);

        System.out.println("Valores de Depósito user 6 después de depositar: " + bdMoneyUpdate);


    }

@Test
public void verificacionDeRetiroDinero() {
    // Consulta al endpoint usuario de Id = 9 "users/9"
    Response userJsonResponse9 = getRequest("users/9");

    // Obtención del objeto del usuario y posteriormente su cuenta
    Map<String, Object> user = userJsonResponse9.jsonPath().getMap("");
    Map<String, Object> accountMoney = (Map<String, Object>) user.get("account");

    //Obtención de valor actual de dinero convertido a objeto BigDecimal y redondeo de valor
    BigDecimal currentMoney = new BigDecimal(userJsonResponse9.jsonPath().getDouble("account.money")).setScale(2, RoundingMode.HALF_UP);
    System.out.println("Valores de Dinero user 9 antes de retiro " + currentMoney);

    BigDecimal extraction = new BigDecimal(110.20); // Valor de retiro
    //Sustracción de valor de retiro al monto actual y redondeo del nuevo valor
    currentMoney = currentMoney.subtract(extraction).setScale(2, RoundingMode.HALF_UP);

    // Agregado del nuevo valor al objeto account
    accountMoney.put("money", currentMoney.doubleValue());

    //Envío de valores a través de método PATCH
    userJsonResponse9 = patchRequest("/users/9", user);

    // Obtención del usuario nuevamente
    userJsonResponse9 = getRequest("users/9");
    //Obtención nuevamente de ultimo valor de dinero convertido a Objeto BigDecimal y redondeo de valor
    BigDecimal obtainingCurrentMoney = new BigDecimal(userJsonResponse9.jsonPath().getDouble("account.money")).setScale(2, RoundingMode.HALF_UP);
    System.out.println("Valores de Dinero user 9 despues  de retiro " + obtainingCurrentMoney);


    }

}
