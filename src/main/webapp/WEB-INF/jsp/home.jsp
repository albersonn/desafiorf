<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <meta charset="UTF-8"/>
  <title></title>
</head>
<body>
  <form method="POST" action="">
    <label for="inpContaOrigem">Conta Origem</label>
    <input type="text" id="inpContaOrigem" required pattern="[0-9]{5}-[0-9]{1}" name="contaOrigem">
    <br />

    <label for="inpContaDestino">Conta Destino</label>
    <input type="text" id="inpContaDestino" required pattern="[0-9]{5}-[0-9]{1}" name="contaDestino">
    <br />

    <label for="inpValor">Valor</label>
    <input type="number" id="inpValor" required name="valor">
    <br />

    <label for="inpDataAgendamento">Data do Agendamento</label>
    <input type="date" id="inpDataAgendamento" required name="dataAgendamento">
    <br />

    <label for="inpTipoAgendamento">Tipo do Agendamento</label>
    <select id="inpTipoAgendamento" name="tipoAgendamento" required>
      <option value=""></option>
      <option value="A">A</option>
      <option value="B">B</option>
      <option value="C">C</option>
      <option value="D">D</option>
    </select>
    <br />

    <input type="submit">

    <script type="text/javascript">

    </script>
  </form>
</body>
</html>
