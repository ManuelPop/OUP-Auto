echo Start Selenium Grid (for Firefox)
ping -n 2 127.0.0.1>nul

start /max /D "D:\Workspace_OUP" selenium-server-standalone-2.45.0.jar -role hub
ping -n 2 127.0.0.1>nul

start /max /D "D:\Workspace_OUP" selenium-server-standalone-2.45.0.jar -role node  -hub http://localhost:4444/grid/register
ping -n 2 127.0.0.1>nul