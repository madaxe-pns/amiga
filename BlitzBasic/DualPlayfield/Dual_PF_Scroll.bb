;
; Dual PlayField OCS/ECS Example
;
; As each BitMap has 3 BitPlanes (8 Colours)
; the Copper must be initiazlied with 6 BitPlanes (3+3)
;

WBStartup

; Creates Foreground BitMap
BitMap 0,640,256,3

; Creates Background BitMap
BitMap 1,320,512,3


; Initializes and Loads the Palette
InitPalette 0,16

LoadPalette 0,"data/palOCS01.pal",8
LoadPalette 0,"data/palOCS02.pal",0

PalRGB 0,0,0,0,0

; Initializes the Copper
InitCopList 0,$36

BLITZ

; Creates the Displays
CreateDisplay 0

; Displays the Palette
DisplayPalette 0,0


For n=0 To 31

  x0=Rnd(309)
  y0=Rnd(245)

  x1=Rnd(309)
  y1=Rnd(245)

  ; Draws Boxes in Foreground BitMap
  Use BitMap 0
    Boxf x0,y0,x0+10,y0+10,n
    Boxf x0+320,y0,x0+330,y0+10,n

  ; Draws Boxes in Background BitMap
  Use BitMap 1
    Boxf x1,y1,x1+10,y1+10,n
    Boxf x1,y1+256,x1+10,y1+266,n

Next n

x=0
y=0

While Joyb(0)=0

  ; Scrolls horizontally the Foreground BitMap
  x+0.5
  x=QWrap(x,0,320)

  ; Scrolls vertically  the Background BitMap
  y+0.5
  y=QWrap(y,0,256)

  ; Displays the BitMaps
  DisplayBitMap 0,0,x,0,1,0,y

  VWait

Wend

End
