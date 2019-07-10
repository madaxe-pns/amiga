;
; Creates a BitMap and draws lines on OCS/ECS - 4 BitPlanes : 16 Colours
;

WBStartup

; Creates the BitMap
BitMap 0,320,256,4

; Initializes the Copper
InitCopList 0,44,256,$4,8,16,0

; Initializes the Palette
InitPalette 0,16

For i=15 To 0 Step -1:PalRGB 0,i,Rnd(15),Rnd(15),Rnd(15):Next

PalRGB 0,0,0,0,0


BLITZ


; Draw the Lines
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
