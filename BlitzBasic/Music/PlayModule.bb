;
; Reads a .MOD file and plays it
;

WBStartup

NPrint "Loading Module, please wait."

; Loads the Module
LoadModule 0,"Helico.mod"

; Plays the Module
PlayModule 0

NPrint ""
NPrint "Playing Module, press Mouse Button to leave."

MouseWait

; Stops Playing the Module
StopModule

End
