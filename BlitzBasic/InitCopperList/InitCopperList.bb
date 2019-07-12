;
; Creates 2 Coppers on OCS/ECS.
; One Copper with Single PlayField and 4 BitPlanes - 16 Colours
; Another Copper with DualPlayField and 6 BitPlanes (3+3 BitPlanes) - 8+8 Colours
;
; Creates a BitMap and displays it in the first Copper.
;
; Creates 2 BitMaps, overlap them using Dual PlayField and displays them
; in the second Copper
;

WBStartup

Dim r.q(3)

r(0)=0
r(1)=Pi/2
r(2)=Pi
r(3)=3*Pi/2

; Creates the BitMap 0 - Background
BitMap 0,320,256,3

; Creates the BitMap 1 - Foreground
BitMap 1,320,256,3

; Creates the BitMap 2 - "Menu"
BitMap 2,320,256,4

; Creates the BitMap 3 - Sprites
BitMap 3,60,14,4


; Loads BitMap 0 - Background
LoadBitMap 0,"data/back.lbm"

; Loads BitMap 1 - Foreground
LoadBitMap 1,"data/fore.lbm"

; Loads BitMap 2 - "Menu"
LoadBitMap 2,"data/menu.lbm"

; Loads BitMap 3 - Sprites
LoadBitMap 3,"data/sprite_16.lbm"

; Gets the Shapes from BitMap 3
Use BitMap 3
GetaShape 0,0,0,14,14
GetaShape 1,15,0,14,14
GetaShape 2,30,0,14,14
GetaShape 3,45,0,14,14

; Gets the Sprites from the Shapes
GetaSprite 0,0
GetaSprite 1,1
GetaSprite 2,2
GetaSprite 3,3

; Releases the Shapes and BitMap 3 - Sprites
Free Shape 0
Free Shape 1
Free Shape 2
Free Shape 3

Free BitMap 3


; Initializes the Palettes

InitPalette 0,32

LoadPalette 0,"data/sprite_16.lbm",16
LoadPalette 0,"data/back.lbm",8
LoadPalette 0,"data/fore.lbm",0

InitPalette 1,16
LoadPalette 1,"data/menu.lbm",0

; Initializes Copper 0
InitCopList 0,$26 ; Dual PlayField

; Initializes Copper 1
InitCopList 1,$4  ; "Menu"


BLITZ

; Creates Display 1
CreateDisplay 1   ; "Menu"

; Displays the Palette 1 in Copper 1
DisplayPalette 1,1

; Displays BitMap 2 in Copper 1 - "Menu"
DisplayBitMap 1,2

While Joyb(0)<>1

Wend

; Creates Display 0
CreateDisplay 0   ; Dual PlayField

; Displays the Palette 0 in Copper 0
DisplayPalette 0,0

; Displays BitMaps 0 and 1 in Copper 0 - Dual PlayField
DisplayBitMap 0,1,0

While Joyb(0)<>2

    ; Rotates the Sprites
    For n=0 To 3

      r(n)+0.05

      x=160+Sin(r(n))*100
      y=128+Cos(r(n))*100

      DisplaySprite 0,n,x,y,n*2

    Next n

    VWait

Wend

End
