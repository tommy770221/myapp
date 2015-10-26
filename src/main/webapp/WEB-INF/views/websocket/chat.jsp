<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Stock Ticker</title>
</head>
<body>
  <h1>Stock Ticker</h1>

  <table>
    <thead><tr><th>Message</th><th>Time</th></tr></thead>
    <tbody id="price"></tbody>
  </table>

  <p class="new">
     Code: <input type="text" class="code"/>
    Price: <input type="text" class="price"/>
    <button class="send">Add</button>
    <button class="remove-all">Remove All</button>
  </p>

  <script src="https://cdn.jsdelivr.net/sockjs/0.3.4/sockjs.min.js"></script>
  <script src="${pageContext.request.contextPath}/jsPlugin/stomp.js"></script>
  <script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
  <script>
    //Create stomp client over sockJS protocol
    var socket = new SockJS("http://localhost:8080/myapp/websocket/");
    var stompClient = Stomp.over(socket);
    // Render price data from server into HTML, registered as callback
    // when subscribing to price topic
    function renderPrice(frame) {
      var messages = JSON.parse(frame.body);
      $('#price').empty();
      for(var i in messages) {
        var message = messages[i];
        $('#price').append(
          $('<tr>').append(
            $('<td>').html(message.message),
            $('<td>').html(message.message),
            $('<td>').html(message.timeStr)
          )
        );
      }
    }
    
    // Callback function to be called when stomp client is connected to server
    var connectCallback = function() {
      stompClient.subscribe('/topic/fleet/${userId}', renderPrice);
    }; 
    // Callback function to be called when stomp client could not connect to server
    var errorCallback = function(error) {
      alert(error.headers.message);
    };
    // Connect to server via websocket
    stompClient.connect("guest", "guest", connectCallback, errorCallback);
    
    // Register handler for add button
    $(document).ready(function() {
      $('.send').click(function(e){
        e.preventDefault();
        var code = $('.new .code').val();
        var jsonstr = JSON.stringify({'message': code});
        stompClient.send("/app/fleet/4028818350787517015078759a220000/driver/4028818350a47b640150a47d0e630000", {}, jsonstr);
        return false;
      });
    });
    
    // Register handler for remove all button
    $(document).ready(function() {
      $('.remove-all').click(function(e) {
        e.preventDefault();
        stompClient.send("/app/removeAllStocks");
        return false;
      });
    });
  </script>
</body>
</html>