import socket
from SoundRecordCommand.database_operation import DDBOperation

command_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
command_socket.bind(("192.168.43.100", 3000))
while True:
    try:
        command_socket.listen()
        command_conn, addr = command_socket.accept()
        command_retrieve = DDBOperation()
        command_string = command_retrieve.retrieve_command("AWS_Verizon_Commands")
        command_conn.send(command_string.encode())
        command_conn.close()
        if command_string != "None":
            command_retrieve.delete_command("AWS_Verizon_Commands")
    except KeyboardInterrupt:
        break
    