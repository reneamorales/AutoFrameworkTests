
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
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
            account.put("money", faker.number().randomDouble(2, 0, 800000));

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
        Assert.assertEquals(response.getStatusCode(), 200);

        //Impresion por consola de datos envíados
        System.out.println(response.asString());

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
        response = getRequest("users/5");

        System.out.println("Usuario modificado " + response.asString());
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
        double dineroActual = userJsonResponse6.jsonPath().getDouble("account.money");
        double nuevoValor = dineroActual + 320.20;

        //Agregado de nuevo valor
        accountMoney.put("money", nuevoValor);

        //Envío a través de método PATCH
        userJsonResponse6 = patchRequest("users/6", user);

        // Obtención del usuario nuevamente
        userJsonResponse6 = getRequest("users/6");
        System.out.println("Usuario modificado " + userJsonResponse6.asString());
    }

@Test
    public void verificacionDeRetiroDinero() {
        //Consulta al endpoint usuario de Id = 9 "users/9"
        Response userJsonResponse9 = getRequest("users/9");

        // Obtención del objeto del usuario y posteriormente su cuenta
        Map<String, Object> user = userJsonResponse9.jsonPath().getMap("");
        Map<String, Object> accountMoney = (Map<String, Object>) user.get("account");

        //Obtenición de valor actual de dinero.
        double dineroActual = userJsonResponse9.jsonPath().getDouble("account.money");
        double extraccion = dineroActual - 110.20; // Descuento de valor

        //Agregado del nuevo valor al objeto money
        accountMoney.put("money", extraccion);

        //Envío de valores a través de método PATCH
        userJsonResponse9 = patchRequest("/users/9", user);

    // Obtención del usuario nuevamente
    userJsonResponse9 = getRequest("users/9");
    System.out.println("Usuario modificado " + userJsonResponse9.asString());
    }

}
