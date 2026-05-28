package co.edu.udc.desechos_fabrica.user.infrastructure.adapter.email;

public record SmtpConfig(
    String host, int port, String username, String password, String fromAddress, String fromName)
{

}
