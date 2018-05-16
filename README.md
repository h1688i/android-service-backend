# android 及時推送服務

* Android 後台服務流程圖
    * 圖中說明使用android service元件做為移動端與遠端建立通訊接收遠端消息流程。
![](android_backend_push_msg_service.png)
* 圖分為三個部分
    * 客戶端介面
        * 推送服務經由客戶端介面啟動,當PushService接收到遠端推送訊息時,在將訊息推送到前端顯示。
    * 系統後端服務
        * CoreService,PushService兩個互相綁定,當其中一方退出,由存活的另一方將重新啟動退出的服務。
        * PushService
            * 系統輪詢:每週期循環喚醒service。
            * Client:登入伺服器後每週期向遠端確認連線,並等待接收遠端訊息。
    * 遠端伺服器
        * 移動端即時訊息來源。