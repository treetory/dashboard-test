/**
 * 객체지향 자바스크립트 연습
 *
 * 아래 객체는 웹 소켓 연결을 위한 stompClient 와 데이터 시각화를 위한 c3.js 의 차트 object,
 * 그리고 소켓 client 와 차트 object 의 이벤트를 연결하여 동작되도록 구성한 것 이다.
 *
 * @type {AAA}
 */
let AAA = (function(){

    // 생성자
    function AAA(id, option, topicURI, sendURI, render_callback) {
        this.id = id;
        this.option = option;
        this.topicURI = topicURI;
        this.sendURI = sendURI;
        this.socket = null;
        this.stompClient = null;
        this.subscription = null;
        this.render = render_callback;
        this.chart = c3.generate(this.option);
        this.drawFlag = false;
        this.drawInterval = 1000;
    }

    //
    /**
     * 메서드 1 : 웹 소켓 연결
     *
     * 한번 connection 이 일어난 후에 disconnetion 이 일어나면(네트워크 에러든, 명시적으로 커넥션을 끊든),
     * 동일한 stompClient (SockJS) 를 이용하여 다시 connection 을 맺을 수 없게 되어 있다. (stomp.js 내부에 그렇게 구현됨)
     * -> 그래서 매번 connection 을 맺을 때 마다, 소켓을 생성한 후에 해당 소켓을 이용하여 stompClient 객체를 생성하여 이 객체로 다시 연결한다.
     */
    AAA.prototype.connect = function() {

        // stompClient 생성
        // 소켓 생성
        this.socket = new SockJS('/gs-guide-websocket');
        this.stompClient = Stomp.over(this.socket);
        this.stompClient.debug = null;
        this.stompClient.heartbeat.outgoing = 20000; // client will send heartbeats every 20000ms
        this.stompClient.heartbeat.incoming = 0;     // client does not want to receive heartbeats from the server

        console.log("Now connect to socket server.");

        // 소켓 연결에 필요한 arguments 생성 (header 와 callback 함수)
        let socketHeader = {};
        let connectCallback = function(frame) {
            console.log(frame);
        }

        let errorCallback = function() {
            console.log("error callback.");
        }

        // 소켓 연결
        this.stompClient.connect(socketHeader, connectCallback, errorCallback);
    }

    // 메서드 2 : 웹 소켓 연결 상태 반환
    AAA.prototype.isConnected = function() {
        return this.stompClient.connected;
    }

    // 메서드 3 : 연결된 웹 소켓에 메시지 전송
    AAA.prototype.send = function(message) {
        if (this.stompClient !== null) {
            this.stompClient.send(this.sendURI, {}, JSON.stringify(message));
        }
    }

    // 메서드 4 : 연결된 웹 소켓에 subscribe callback 함수 등록
    AAA.prototype.addSubscription = function() {

        // 객체 내부에 선언되어 있는 property 들의 reference 생성
        // ->  stompClient obj 의 내부 메서드를 재정의 하는데, 이 메서드 블록을 벗어난 범주의 속성값들을 찾을 수 없는 문제가 발생한다.
        //     그래서 해당 속성값을 찾을 수 있도록, reference 하는 변수 또는 함수 객체를 선언한다.
        let _chart = this.chart;
        let _topicURI = this.topicURI;
        let _sendURI = this.sendURI;
        let _renderCallback = this.render;
        let _drawFlag = this.drawFlag;
        let _drawInterval = this.drawInterval;
        let _client = this.stompClient;

        // 구독이 일어났을 때, callback 처리
        this.subscription = this.stompClient.subscribe(_topicURI, function (data) {
            // 받은 데이터 가지고 차트 그린다.
            _renderCallback(eval(data.body), _chart);
            // 만약 drawFlag 이 참이면
            if (_drawFlag) {
                // 설정된 drawInterval 만큼 대기 (fixed delay) 후, 데이터를 다시 구독하기 위해 socket server 로 메시지를 전송한다.
                setTimeout(function() {
                    _client.send(_sendURI, {}, JSON.stringify({ "key" : "value" }));
                }, _drawInterval);
            }
        });
    }

    // 메서드 5 : 차트 drawing 을 시작한다.
    AAA.prototype.startDrawing = function(message, drawInterval) {
        if (!this.drawFlag) {
            // 객체의 차트 렌더링 관련 property 설정
            this.drawFlag = true;
            this.drawInterval = drawInterval;
            // socket client 의 subscribe callback 을 등록한다.
            if (this.subscription == null) {
                this.addSubscription();
            }
            // socket server 에 message 를 전송하여 구독을 시작한다.
            this.send(message);
        } else {
            console.log("Already start to draw the chart continuously.")
        }
    }

    // 메서드 6 : 차트 drawing 을 끝낸다.
    AAA.prototype.stopDrawing = function() {
        // 객체의 차트 렌더링 관련 property 를 초기값으로 돌린다.
        this.drawFlag = false;
        this.drawInterval = 1000;
        // 구독을 취소한다.
        this.subscription.unsubscribe();
        this.subscription = null;
        // socket client 연결을 종료한다.
        console.log("stop to draw the chart.")
    }

    /**
     * 메서드 7 : 웹 소켓 연결 해제
     *
     * 명시적으로 disconnection 을 시킬 때는 stompClient 와 SockJS 를 모두 destroy 시킨다.
     * -> 어차피 disconnection 된 후엔 해당 소켓과 클라이언트로 재접속이 불가하다.
     */
    AAA.prototype.disconnect = function() {
        if (this.stompClient !== undefined || this.stompClient !== null) {
            let disconnectCallback = function() {
                console.log("Disconnected.");
            }
            this.stompClient.disconnect(disconnectCallback);
            this.stompClient = null;
            this.socket = null;
        }
    }

    return AAA;

}());


let aaa = null;
let btn_start = null;
let btn_stop = null;
let btn_connect = null;
let btn_disconnect = null;

window.onload = function(e) {

    let render_callback = function(data, chart) {

        //console.log(data);

        let columns = new Array();
        for (let i=0; i<data.length; i++) {

            let key = Object.keys(data[i]);
            let values = Object.values(data[i]);
            values[0].unshift(key[0]);
            columns.push(values[0]);

            key = null;
            values = null;
        }

        chart.load({
            columns: columns
        });

        columns = null;
    };

    // 객체 생성
    aaa = new AAA(
        'line',
        {
            bindto: '#line',
            data: {
                columns: []
            },
        },
        "/topic/line",
        "/socket/line",
        render_callback
        );

    btn_start = document.getElementById("start");
    btn_start.onclick = function() {
        // 시작
        setTimeout(function() {
            aaa.startDrawing({ "aaa" : "bbb" }, 1000);
        }, 2000);

    }

    btn_stop = document.getElementById("stop");
    btn_stop.onclick = function() {
        // 종료
        setTimeout(function() {
            aaa.stopDrawing();
        }, 300);

    }

    btn_connect = document.getElementById("connect");
    btn_connect.onclick = function() {
        // 연결
        aaa.connect();

    }

    btn_disconnect = document.getElementById("disconnect");
    btn_disconnect.onclick = function() {
        // 연결해제
        aaa.disconnect();

    }

};

window.onbeforeunload = function(e) {
    aaa.disconnect();
    return "Closed?";
}
