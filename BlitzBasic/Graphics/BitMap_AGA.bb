;
; Loads and Creates a BitMap on AGA - 8 BitPlanes : 256 Colours
; Loads 4 Shapes from 4 different BitMaps and Displays it
; Draws Lines
;

WBStartup

; Creates the BitMap
BitMap 0,320,256,8

; Loads the BitMap
LoadBitMap 0,"data/spr_256.lbm"

; Loads the Palette from the BitMap
LoadPalette 0,"data/spr_256.lbm"

; Initializes and Loads the 1st Shape
InitShape 0,46,32,8
LoadShape 0,"data/00_256.lbm"

; Initializes and Loads the 2nd Shape
InitShape 1,46,32,8
LoadShape 1,"data/01_256.lbm"

; Initializes and Loads the 3rd Shape
InitShape 2,30,48,8
LoadShape 2,"data/02_256.lbm"

; Initializes and Loads the 4th Shape
InitShape 3,48,48,8
LoadShape 3,"data/03_256.lbm"

; Initializes the Copper
InitCopList 0,44,256,$10008,8,256,0

; Displays the Palette
DisplayPalette 0,0


BLITZ


Line 0,120,319,120,200

; Draws the Lines
For x=0 To 255
  For y=50 To 55
    Plot x,y+80,x
  Next y
Next x

; Displays the Shapes
Blit 0,50,150
Blit 1,100,150
Blit 2,150,150
Blit 3,200,150


; Creates the Display
CreateDisplay 0

; Displays the BitMap
DisplayBitMap 0,0


MouseWait

End
