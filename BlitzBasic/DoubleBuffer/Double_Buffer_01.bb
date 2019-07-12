;
; Double Buffer Example
;

WBStartup

; Creates the BitMaps
BitMap 0,320,256,4
BitMap 1,320,256,4

; Draws a Box and uses it as a Shape
Use BitMap 0
Boxf 0,0,15,15,1

GetaShape 0,0,0,16,16

; Uses Queue Blits. We need one Queue for each BitMap
Queue 0,16
Queue 1,16

; Initializes the Copper
InitCopList 0,44,256,$4,8,16,0

; Initializes the Palette
InitPalette 0,16

PalRGB 0,0,0,0,0
PalRGB 0,1,15,15,15
PalRGB 0,2,0,0,15

BLITZ

; Creates the Display
CreateDisplay 0
DisplayPalette 0,0

; Sets the BitMap 0 to be displayed
bit=0

For y=0 To 240 Step 2

  ; Draws the Shape
  Gosub blitshape

  VWait

  ; Displays the BitMap
  DisplayBitMap 0,bit

  ; Swaps the BitMap
  bit=1-bit

Next y

MouseWait

End


.blitshape

  ; Erases all the Shapes
  UnQueue bit

  Use BitMap bit

  ; Draws the Shapes
  For x=0 To 15
    QBlit bit,0,x*20,y
  Next x

Return
