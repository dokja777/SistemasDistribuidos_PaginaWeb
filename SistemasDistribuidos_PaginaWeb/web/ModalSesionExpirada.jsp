

<div id="myModal" class="modal">
<div class="modal-content">
    <p>Tu sesi�n expirar� en <span id="countdown"></span> segundos.</p>
</div>
</div>


<style>
    .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgb(0,0,0);
            background-color: rgba(0,0,0,0.4);
            padding-top: 60px;
        }
        .modal-content {
            background-color: #fefefe;
            margin: 5% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
            text-align: center;
        }
        
        span{
            font-size: 3.5vh;
            margin: 0.5vh;
        }
</style>
