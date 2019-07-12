;
; Loads an .IFF file and Plays it
;

WBStartup

NPrint "Loading IFF Sound, please wait."

; Loads the Sample
LoadSound 0,"pexplode.iff"

; Plays the Sample
Sound 0,1

NPrint ""
NPrint "Playing IFF Sound, press Mouse Button to leave."

MouseWait

End
