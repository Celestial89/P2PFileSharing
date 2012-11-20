Introduction:
This program is designed by the Model-View-Controller (MVC) pattern and Observer pattern. The main function mudules
are separates into three parts: model, view and controller. Every view is an observer, once the model is change, view
will update itself automatically. And controller used to handling events and change model. Views use update(Model model) 
method to update themselves and models use registerObserver(Observer observer) and removeObserver(Observer observer) 
methods to add and delete their observer. And controller use setModel(Model model) method to associate with its model. 
The program has five sub-views, and the Mainview assemble them to a whole view. In order to separate view and controller, 
I do not provide the Listeners in the view, and provide them in the controller. Thus the Listeners will be added when 
the program is running.

Add features:
1.When you connected to your friend, the client of your friend will add you automatically.
2.When you closed your program, the client of your friend will delete you automatically.
3.You can set and change your name if you want.
4.The software use SQLite database to store the path of file which you want to share, thus you can share files locate 
in any places in your PC.
5.The software can show the its state, like connect successful and the rate of download.
6.You can download more than one file at same time.

There are 47 files in this program, the structure of this program as follows:
1.Base files:
Client.java: The entrance of software, I defined the global variables here, and start the function modules.
File.java: Entity class of file, store the information of file, include id, name, path.
Friend.java: Entity class of friend, store the information of friend, include name, ip.

2.Interfaces:
Controller.java: the interface of controller who has one method, setModel.
Model.java: the interface of model, there are two method here, registerObserver and removeObserver.
Observer.java: the interface of observer, only one method in the interface, update.
Module.java: the interface of module, has one method, run. Every function module calls other function via this interface.

3.Closing function:
This function happened when you close the software. It send the close message to the friends you are connected, and
your friends' program delete you from their friend list. After the process will show the off line message to your
friends.
There are 2 files: ClosingModule.java, ClisingThread.java.

4.Connect function:
This function used to connect to your friend, add friend to your friend list and show the connect information. If
conncet successful, the client of your friend will also add you to his friend list and show connect successful message
to your friend. And I limit the TextField only can input '.' and number.
There are 5 files: ConnectModule.java, ConnectController.java, ConnectView.java, ConnectThread.java, NameServerThread.java.

5.MyFile function:
This function is used to update, show and send self's file list. The information of self's file is stored in the 
database, I use a small database called "sqlite" in the program. The database file is called "FilesDB.db". Use 
database you can share files located in every place in the PC, and do not need to provide a special folder to store 
shared files.
There are 5 files: MyFileModule.java, FilesModel.java, MyFileView.java, MyFileController.java, FilesServerThread.java.

6.Download function:
This function is download files from your friend. You can download more than one files at same time, and the files 
will been saved in the "download" folder. State panel will show the rate of download progress.
There are 3 files: DownloadModule.java, FilesReceiveThread.java, FilesSendThread.java.

7.State function:
This function is used to show the state of program. The word has three different colours to represent different state. 
And the state will change back after a given time.
There are 5 files: StateModule.java, StateView.java, StateModel.java, StateController.java, InitStateThread.java.

8.Friends function:
This function is used to update the friends list.
There are 4 files: FriendsModule.java, FriendsView.java, FriendsModel.java, FriendsController.java.

9.Initialize information function:
This function start at the beginning of the program is used to get self's infromation, like name and ip. 
There is 1 file: InitInfoModule.java.

10.Main function:
This is initialize the main frame of this program and compose the sub-view to a whole view.
There are 3 files: MainModule.java, MainController.java, MainView.java.

11.Opposite file function:
This function is used to get, show and update friend's file list. When you select one friend, the file list of the 
friend will show in the OppFileView. If the friend's file list has changed, press the "refresh" button and select the 
friend again, the new file list will show.
There are 4 files: OppFileModule.java, OppFileView.java, OppFileController.java, OppFileThread.java.

12.Set name function:
This function is used to set user's name. The user's name is store in the file which called "userInfo.txt". If you first use this program, this function will ask you to set your name
automatically. You also can press the "SetName" button to change your name.
There are 4 files: SetNameModule.java, SetNameView.java, SetNameModel.java, SetNameController.java.

13.Share function:
This function is used to update my file list and database. You can add and delete the file you want to share, and you 
can press "Finish" button to end your change.
There are 3 files: ShareModule.java, ShareView.java, ShareController.java.

14. Thread function:
Start the threads which is need to start at the beginning of the program. 
There is 1 file: ThreadModule.java.

Step for running:
1)Import Project:
Open eclipse, select File->Import->General->Existing Projects into Workspace
click next button, then select the root directory "P2PFileSharing", and click finish button.
2)Running Project:
run the client: the file called Client.java.
3)Operate:
If you are the first time running the program, the program will ask you to input your name. Input your name and click
the OK button and click the Share button to add the files you want to share, then click Finish button to back to main
frame. In order to conncet to your friend, you need to input your friend's ip address and click Connect button.

Note: if you run the program via cmd.exe, please add the database driver to the classpath. The driver is located in
the "lib" folder, called "sqlitejdbc-v056.jar".

The program has been tested in the MyEclipse 8.6