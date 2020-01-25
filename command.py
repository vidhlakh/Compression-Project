# -*- coding: utf-8 -*-
"""
Created on Sat May 25 10:35:27 2019

@author: vidhl
"""

import abc

class Command(metaclass=abc.ABCMeta):
    """
    Declare an abstract base class for executing an operation.
    """

    def __init__(self, receiver):
        self._receiver = receiver

    @abc.abstractmethod
    def execute(self):
        pass
    def undo(self):
        pass

class AppendCommand(Command):
    """ Command to append a letter to a string  """
    def __init__(self,receiver,letter):
        self._receiver= receiver
        self.previous_Text=None
        self._letter=letter
    def execute(self):
        self.previous_Text=self._receiver.samplestr
        self._receiver.append(self._letter)
    def undo(self):
        self._receiver.samplestr=self._receiver.samplestr.strip(self._letter)
        print("After undoing append function : ",self.previous_Text)
        
class DeleteStartCommand(Command):
    """ Command to delete a letter from the start of a string  """
    def __init__(self,receiver):
        self._receiver= receiver
        self.previous_Text=None
        self._letter=""
    def execute(self):
        self.previous_Text=self._receiver.samplestr
        self._letter=self._receiver.delete_start()
    def undo(self):
        self._receiver.samplestr=self._letter+self._receiver.samplestr
        print("After undoing delete first letter : ",self.previous_Text)

class DeleteEndCommand(Command):
    """ Command to delete a letter from the end of a string"""
    def __init__(self,receiver):
        self._receiver= receiver
        self.previous_Text=None
        self._letter=""
    def execute(self):
        self.previous_Text=self._receiver.samplestr
        self._letter=self._receiver.delete_end()
    def undo(self):
        self._receiver.samplestr=self._receiver.samplestr+self._letter
        print("After undoing delete last letter : ",self.previous_Text)

class CapitalizeCommand(Command):
    """ Command to capitalize a letter at any index within a string"""
    def __init__(self,receiver,index):
        self._receiver= receiver
        self.previous_Text=None
        self._index=index
    def execute(self):
        self.previous_Text=self._receiver.samplestr
        self._receiver.capitalize(self._index)
    def undo(self):
        self._receiver.samplestr=''.join(value.lower() if pos==self._index and value.isupper() else value for pos, value in enumerate(self._receiver.samplestr))
        print("After undoing capitalize function : ",self.previous_Text)
class LowercaseCommand(Command):
    """ Command to lower a letter at any index within a string"""
    def __init__(self,receiver,index):
        self._receiver= receiver
        self.previous_Text=None
        self._index=index
        self.oldChar = None
        
    def execute(self):
        self.oldChar = self._receiver.getAtIndex(self._index)
        self.previous_Text=self._receiver.samplestr
        self._receiver.lower(self._index)
    def undo(self):
        self._receiver.samplestr=''.join(self.oldChar if pos==self._index  else value for pos, value in enumerate(self._receiver.samplestr))
        print("After undoing lowercase function : ",self.previous_Text)
        
class TitlecaseCommand(Command):
    """ Command to titlecase whole string"""
    def __init__(self,receiver):
        self._receiver= receiver
        self.previous_Text=None
    def execute(self):
        self.previous_Text=self._receiver.samplestr
        self._receiver.title()
        
    def undo(self):
        self._receiver.samplestr=self.previous_Text
        print("After undoing title function : ",self.previous_Text)
''' Receiver class'''
class stredit_Receiver:
    """    Perform the operations associated with each command """
    
    def __init__(self,samplestring):
        self.samplestr=samplestring
        self._letter=""
    def action(self):
        pass
    def append(self,letter):
        self.samplestr+=letter
        print("After appending : "+self.samplestr)
    def delete_start(self):
        self._letter=self.samplestr[0]
        self.samplestr=self.samplestr[1:]
        print("After deleting first letter : "+self.samplestr)
        return self._letter
    def delete_end(self):
        self._letter=self.samplestr[-1]
        self.samplestr=self.samplestr[:-1]
        print("After deleting last letter : "+self.samplestr)
        return self._letter
    def capitalize(self,index):
        self.samplestr=''.join(value.upper() if pos==index else value for pos, value in enumerate(self.samplestr))
        print("After capitalizing random index, say 3 : "+self.samplestr)
    def lower(self,index):
        self.samplestr=''.join(value.lower() if pos==index else value for pos, value in enumerate(self.samplestr))
        print("After lowercasing random index, say 2 : "+self.samplestr)
    def getAtIndex(self,index):
        return self.samplestr[index]
    def title(self):
        self.samplestr=self.samplestr.title()
        print("After title case : "+self.samplestr)
        '''Invoker class'''
class Invoker:
    """
    Call the command to carry out the request.
    """

    def __init__(self):
        self._commands = []

    def store_command(self, command):
        self._commands.append(command)
        #print("store commands in _command list",self._commands)
    def execute_commands(self):
        for command in self._commands:
            command.execute()

    def undo_actions(self):
        self._commands.reverse()
        print("Undoing...")
        for command in self._commands:
            command.undo()
    def redo_actions(self):
        self._commands.reverse()
        print("Redoing...")
        for command in self._commands:
            command.execute()
'''Client'''
def main():
    receiver = stredit_Receiver("command pattern")
    append_command = AppendCommand(receiver,"s")
    delete_start_command=DeleteStartCommand(receiver)
    delete_end_command=DeleteEndCommand(receiver)
    capitalize_command = CapitalizeCommand(receiver,3)
    lowercase_command = LowercaseCommand(receiver,2)
    titlecase_command = TitlecaseCommand(receiver)
    invoker = Invoker()
    invoker.store_command(append_command)
    invoker.store_command(delete_start_command)
    invoker.store_command(delete_end_command)
    invoker.store_command(capitalize_command)
    invoker.store_command(lowercase_command)
    invoker.store_command(titlecase_command)
    invoker.execute_commands()
    invoker.undo_actions()
    invoker.redo_actions()


if __name__ == "__main__":
    main()