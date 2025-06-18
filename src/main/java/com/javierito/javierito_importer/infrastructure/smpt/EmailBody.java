package com.javierito.javierito_importer.infrastructure.smpt;

import org.springframework.stereotype.Service;

@Service
public class EmailBody {
    public String BuildCredentialBody(String name, String lastName, String username, String password) {
        if (username != null && password != null && name != null && lastName != null) {
            return String.format("""
                <!DOCTYPE html>
                    <html>
                    <head>
                        <style>
                            body {
                                font-family: Arial, sans-serif;
                                color: #333333;
                                background-color: #f4f4f9;
                            }
                            .container {
                                max-width: 600px;
                                margin: 0 auto;
                                background-color: #ffffff;
                                padding: 20px;
                                border-radius: 8px;
                                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
                            }
                            h2 {
                                color: #f97316;
                            }
                            .content {
                                line-height: 1.6;
                                font-size: 16px;
                            }
                            .footer {
                                margin-top: 20px;
                                font-size: 14px;
                                color: #666666;
                                text-align: center;
                            }
                            .button {
                                display: inline-block;
                                padding: 10px 20px;
                                margin-top: 20px;
                                color: #ffffff;
                                background-color: #f97316;
                                text-decoration: none;
                                border-radius: 5px;
                                font-size: 16px;
                            }
                        </style>
                    </head>
                    <body>
                        <div class="container">
                            <h2>Hola, %s %s</h2>
                            <div class="content">
                                <p>¡Bienvenido! Aquí tienes tus credenciales de acceso al sistema:</p>
                                <p><strong>Usuario:</strong> %s<br>
                                <strong>Contraseña:</strong> %s</p>
                                <p>Por favor, asegúrate de cambiar tu contraseña al iniciar sesión para proteger tu cuenta.</p>
                                <a href="#" class="button">Ir a la página de inicio de sesión</a>
                            </div>
                            <div class="footer">
                                <p>Saludos,<br>
                                El equipo de soporte.</p>
                                <p>Este correo es confidencial y solo debe ser usado por el destinatario.</p>
                            </div>
                        </div>
                    </body>
                    </html>
                """, name, lastName, username, password);
        }
        return null;
    }
}
