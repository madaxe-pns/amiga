;
; Double Buffer Examples
;

WBStartup

; Creates 2 BitMaps for Double Buffer
BitMap 0,320,256,4
BitMap 1,320,256,4

; Initializes and loads the Shape
InitShape 0,16,16,2
LoadShape 0,"nave.lbm"

; Uses Buffer Blits, don't damage the background.
; We need a buffer for each BitMap
Buffer 0,8192
Buffer 1,8192

; Initializes the Copper
InitCopList 0,44,256,$4,8,16,0

; Initializes and Loads the Palettes
InitPalette 0,16

LoadPalette 0,"nave.lbm",0

PalRGB 0,0,0,0,0

For n=4 To 15
  PalRGB 0,n,Rnd(15),Rnd(15),Rnd(15)
Next n

; Draws Some Rectangles.
; We need to draw the same rectangles in the 2 BitMaps
For n=0 To 20

  x0=Rnd(159)
  y0=Rnd(127)
  x1=Rnd(159)+160
  y1=Rnd(127)+128
  c=Rnd(15)

  Use BitMap 0
    Box x0,y0,x1,y1,c
  Use BitMap 1
    Box x0,y0,x1,y1,c
Next n

BLITZ

; Creates the Display
CreateDisplay 0
DisplayPalette 0,0

; Sets BitMap 0 to be Displayed
bit=0

For y=0 To 240 Step 2

  ; Draws the Shapes
  Gosub blitshape

  VWait

  ; Displays de BitMap
  DisplayBitMap 0,bit

  ; Swaps the BitMap
  bit=1-bit

Next y

MouseWait

End


.blitshape

  ; Erases all the Shapes keeping the Background
  UnBuffer bit

  Use BitMap bit

  ; Draws the Shapes
  For x=0 To 15
    BBlit bit,0,x*20,y
  Next x

Return
