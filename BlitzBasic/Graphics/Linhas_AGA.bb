;
; Creates a BitMap and draws Lines on AGA - 8 BitPlanes : 256 Colours
;

WBStartup

; Creates the BitMap
BitMap 0,320,256,8

; Initializes the Copper
InitCopList 0,44,256,$10008,8,256,0

; Initializes the Palette
InitPalette 0,255

For i=255 To 0 Step -1:AGAPalRGB 0,i,Rnd(255),Rnd(255),Rnd(255):Next

AGAPalRGB 0,0,0,0,0


BLITZ


; Draws the Lines
For i= 0 To 255
  Line 0,i,320,i,i
Next

; Creates the Display
CreateDisplay 0

DisplayPalette 0,0

; Displays the BitMap
DisplayBitMap 0,0

MouseWait

End
