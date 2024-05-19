/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */



var totalInactiveTime = 10; // 10 segundos
        var warningTime = 5; // 5 segundos antes del logout
        var time, warningTimer;

        window.onload = function() {
            resetTimer();
            document.addEventListener('mousemove', resetTimer, false);
            document.addEventListener('keypress', resetTimer, false);
        }

        function startLogoutWarning() {
            var countdown = warningTime;
            var modal = document.getElementById("myModal");
            var countdownElement = document.getElementById("countdown");
            modal.style.display = "block";
            countdownElement.innerText = countdown;

            warningTimer = setInterval(function() {
                countdown--;
                countdownElement.innerText = countdown;
                if (countdown < 0) {
                    clearInterval(warningTimer);
                    logout();
                }
            }, 1000);
        }

        function logout() {
            alert("Tu sesión ha expirado.");
            window.location.href = 'CerrarSesion'; /*Es el controlador que hice para que se cierre */
        }


        function resetTimer() {
            clearTimeout(time);
            clearInterval(warningTimer); // Detener el contador de advertencia si está en curso
            var modal = document.getElementById("myModal");
            modal.style.display = "none"; // Ocultar el modal si se muestra
            time = setTimeout(startLogoutWarning, (totalInactiveTime - warningTime) * 1000);
        }
