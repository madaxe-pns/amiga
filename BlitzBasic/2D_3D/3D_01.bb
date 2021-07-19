;
; Performs a 3D Rotation of a Cube
; using a Sin and Cos Table
; created previously
;

WBStartup

; Sin and Cos Table
NEWTYPE .sincos
  s.q[126]
  c.q[126]
End NEWTYPE

DEFTYPE .sincos sincos

; Reads the Sin and Cos Table from a File
If ReadFile (0,"sincostab.dat")

  ReadMem 0,&sincos,SizeOf.sincos

  CloseFile 0

End If

NEWTYPE .pontos3d
  x.q
  y.q
  z.q
End NEWTYPE

NEWTYPE .pontos2d
  x.q
  y.q
End NEWTYPE

Dim List Lpontos3d.pontos3d(7)  ; Local Object Coordinates - 3D
Dim List Wpontos3d.pontos3d(7)  ; Virtual World Coordinates - 3D
Dim List Apontos3d.pontos3d(7)  ; Rotation Coordinates - 3D

Dim List Spontos2d.pontos2d(7)  ; Screen Coordinates - 2D

NEWTYPE .xy2d
  xc.w
  yc.w
End NEWTYPE

Dim xy.xy2d(7)  ; Lines of our Object

; Rotation Angles
angx.q=0:angy.q=0:angz.q=0

; Virtual World Coordinates
wx.q=0:wy.q=0:wz.q=0

; Rotation Coordinates
ax.q:ay.q:az.q

; Screen Coordinates
sx.q:sy.q
sw.w=320:sh.w=215

NF.w=100        ; Focal Lenght
scalling.w=75   ; Scale Factor

; Position of our Object in Virtual World
XFIXFACTOR.w=0:YFIXFACTOR.w=0:ZFIXFACTOR.w=300

xxx.w=0:yyy.w=1:zzz.w=0

tmpx.q=0:tmpy.q=0:tmpz.q=0

cl=0
exit=0

; Creates our Local Object (in this case a cube)

If AddItem(Lpontos3d())
  Lpontos3d()\x=-1
  Lpontos3d()\y=-1
  Lpontos3d()\z=-1
End If

If AddItem(Lpontos3d())
  Lpontos3d()\x=1
  Lpontos3d()\y=-1
  Lpontos3d()\z=-1
End If

If AddItem(Lpontos3d())
  Lpontos3d()\x=1
  Lpontos3d()\y=-1
  Lpontos3d()\z=1
End If

If AddItem(Lpontos3d())
  Lpontos3d()\x=-1
  Lpontos3d()\y=-1
  Lpontos3d()\z=1
End If

If AddItem(Lpontos3d())
  Lpontos3d()\x=-1
  Lpontos3d()\y=1
  Lpontos3d()\z=-1
End If

If AddItem(Lpontos3d())
  Lpontos3d()\x=1
  Lpontos3d()\y=1
  Lpontos3d()\z=-1
End If

If AddItem(Lpontos3d())
  Lpontos3d()\x=1
  Lpontos3d()\y=1
  Lpontos3d()\z=1
End If

If AddItem(Lpontos3d())
  Lpontos3d()\x=-1
  Lpontos3d()\y=1
  Lpontos3d()\z=1
End If

; Virtual World Coordinates
While AddItem(Wpontos3d())
  Wpontos3d()\x=0
  Wpontos3d()\y=0
  Wpontos3d()\z=0
Wend

; Rotation Coordinates
While AddItem(Apontos3d())
  Apontos3d()\x=0
  Apontos3d()\y=0
  Apontos3d()\z=0
Wend

; Screen Coordinates
While AddItem(Spontos2d())
  Spontos2d()\x=0
  Spontos2d()\y=0
Wend



BitMap 0,320,210,1  ; Our Object BitMap

BitMap 2,320,43,5   ; Panel BitMap

InitShape 0,7,7,2        ; Creates our Mouse Pointer
LoadShape 0,"cursor.lbm"
GetaSprite 0,0
Free Shape 0

InitPalette 0,32

PalRGB 0,0,0,0,0
PalRGB 0,1,15,15,15
PalRGB 0,2,15,15,0
PalRGB 0,3,0,10,15
PalRGB 0,4,0,15,0

LoadPalette 0,"cursor.lbm",16 ; Loads our Mouse Pointer Palette

InitCopList 0,44,210,$1,8,2,0   ; CopList our Object
InitCopList 1,256,43,$5,8,32,0  ; CopList Panel

BLITZ

Use BitMap 2
Mouse On
MouseArea 0,0,319,42
BitMapInput
BitMapOutput 2

CreateDisplay 0,1

DisplayPalette 0,0
DisplayBitMap 0,0

DisplayPalette 1,0
DisplayBitMap 1,2

Gosub DrawPanel

DisplaySprite 1,0,MouseX,MouseY,0

.MainLoop

While exit=0

  DisplaySprite 1,0,MouseX,MouseY,0

  If xxx=1 Gosub XXRotation ; Calculates all Coordinates
  If yyy=1 Gosub YYRotation
  If zzz=1 Gosub ZZRotation

  Gosub CalculaCoords

  If Joyb(0)=1 AND OldJoyb=0 Gosub CheckBoxes

  OldJoyb=Joyb(0)

  VWait

  Gosub DrawFigure    ; Draw our Object

Wend

End


; Calculates All Coordinates
.CalculaCoords

  Gosub CriaWorldCoords
  Gosub CriaAlignCoords
  Gosub CriaScreenCoords

Return

; Calculates World Coordinates
.CriaWorldCoords

  ResetList Lpontos3d()
  ResetList Wpontos3d()

  While NextItem (Lpontos3d())

    NextItem Wpontos3d()

    Wpontos3d()\x=scalling*Lpontos3d()\x+wx
    Wpontos3d()\y=scalling*Lpontos3d()\y+wy
    Wpontos3d()\z=scalling*Lpontos3d()\z+wz

  Wend

Return

; Calculates Rotation Coordinates
.CriaAlignCoords

  Gosub CriaAlignPointCoords
  Gosub CriaAlignOrigiCoords

Return

; Rotation Dots Coordinates
.CriaAlignPointCoords

  ResetList Lpontos3d()
  ResetList Wpontos3d()
  ResetList Apontos3d()

  While NextItem(Lpontos3d())

    NextItem Wpontos3d()
    NextItem Apontos3d()

; XX Axis
    tmpx=Wpontos3d()\x
    tmpy=Wpontos3d()\y
    tmpz=Wpontos3d()\z

    Apontos3d()\x=tmpx
    Apontos3d()\y=tmpy*sincos\c[angx]+tmpz*sincos\s[angx]
    Apontos3d()\z=-tmpy*sincos\s[angx]+tmpz*sincos\c[angx]

; YY Axis
    tmpx=Apontos3d()\x
    tmpy=Apontos3d()\y
    tmpz=Apontos3d()\z

    Apontos3d()\x=tmpx*sincos\c[angy]-(tmpz*sincos\s[angy])
    Apontos3d()\y=tmpy
    Apontos3d()\z=(tmpx*sincos\s[angy])+tmpz*sincos\c[angy]

; ZZ Axis
    tmpx=Apontos3d()\x
    tmpy=Apontos3d()\y
    tmpz=Apontos3d()\z

    Apontos3d()\x=tmpx*sincos\c[angz]-tmpy*sincos\s[angz]
    Apontos3d()\y=(tmpx*sincos\s[angz])+tmpy*sincos\c[angz]
    Apontos3d()\z=tmpz

    Apontos3d()\x=Apontos3d()\x+XFIXFACTOR
    Apontos3d()\y=Apontos3d()\y+YFIXFACTOR
    Apontos3d()\z=Apontos3d()\z+ZFIXFACTOR

  Wend

Return

; Rotation Origin Coordinates
.CriaAlignOrigiCoords

; XX Axis
  tmpx=wx
  tmpy=wy
  tmpz=wz

  ax=tmpx
  ay=(tmpy*sincos\c[angx])+(tmpz*sincos\s[angx])
  az=(-tmpy*sincos\s[angx])+(tmpz*sincos\c[angx])

; YY Axis
  tmpx=ax
  tmpy=ay
  tmpz=az

  ax=tmpx*sincos\c[angy]-(tmpz*sincos\s[angy])
  ay=tmpy
  az=(tmpx*sincos\s[angy])+(tmpz*sincos\c[angy])

; ZZ Axis
  tmpx=ax
  tmpy=ay
  tmpz=az

  ax=tmpx*sincos\c[angz]-tmpy*sincos\s[angz]
  ay=(tmpx*sincos\s[angz])+(tmpy*sincos\c[angz])
  az=tmpz

  ax=ax+XFIXFACTOR
  ay=ay+YFIXFACTOR
  az=az+ZFIXFACTOR

Return

; Calculates Screen Coordinates
.CriaScreenCoords

  Gosub CriaScreenPointCoords
  Gosub CriaScreenOrigiCoords

Return

; Screen Dots Coordinates
.CriaScreenPointCoords

  ResetList Apontos3d()
  ResetList Spontos2d()

  While NextItem (Apontos3d())

    NextItem Spontos2d()

    If Apontos3d()\z=0
      Spontos2d()\x=Apontos3d()\x*NF+sw/2
      Spontos2d()\y=Apontos3d()\y*-NF+sh/2
    Else
      Spontos2d()\x=(Apontos3d()\x*NF)/Apontos3d()\z+sw/2
      Spontos2d()\y=(Apontos3d()\y*-NF)/Apontos3d()\z+sh/2
    End If

  Wend

Return

; Screen Origin Coordinates
.CriaScreenOrigiCoords

  If az=0
    sx=ax*NF+sw/2
    sy=ay*-NF+sh/2
  Else
    sx=(ax*NF)/az+sw/2
    sy=(ay*-NF)/az+sh/2
  End If

Return

; XX Axys Rotation
.XXRotation

  angx+1
  If angx=126 angx=0

Return

; YY Axis Rotation
.YYRotation

  angy+1
  If angy=126 angy=0

Return

; ZZ Axis
.ZZRotation

  angz+1
  If angz=126 angz=0

Return


; Draws / Erases our Object
.DrawFigure

  Use BitMap bit

; Erases the Previous Figure

  cl=0

; Bottom

  Line xy(0)\xc,xy(0)\yc,xy(1)\xc,xy(1)\yc,cl
  Line xy(1)\xc,xy(1)\yc,xy(2)\xc,xy(2)\yc,cl
  Line xy(2)\xc,xy(2)\yc,xy(3)\xc,xy(3)\yc,cl
  Line xy(3)\xc,xy(3)\yc,xy(0)\xc,xy(0)\yc,cl

; Top

  Line xy(4)\xc,xy(4)\yc,xy(5)\xc,xy(5)\yc,cl
  Line xy(5)\xc,xy(5)\yc,xy(6)\xc,xy(6)\yc,cl
  Line xy(6)\xc,xy(6)\yc,xy(7)\xc,xy(7)\yc,cl
  Line xy(7)\xc,xy(7)\yc,xy(4)\xc,xy(4)\yc,cl

; Lines

  Line xy(0)\xc,xy(0)\yc,xy(4)\xc,xy(4)\yc,cl
  Line xy(1)\xc,xy(1)\yc,xy(5)\xc,xy(5)\yc,cl
  Line xy(2)\xc,xy(2)\yc,xy(6)\xc,xy(6)\yc,cl
  Line xy(3)\xc,xy(3)\yc,xy(7)\xc,xy(7)\yc,cl


; Draws the New Figure

  n=0

  ResetList Spontos2d()

  While NextItem (Spontos2d())
    xy(n)\xc=Int(Spontos2d()\x)
    xy(n)\yc=Int(Spontos2d()\y)
    n+1
  Wend
                                                                                                                              
  cl=1

; Bottom

  Line xy(0)\xc,xy(0)\yc,xy(1)\xc,xy(1)\yc,cl
  Line xy(1)\xc,xy(1)\yc,xy(2)\xc,xy(2)\yc,cl
  Line xy(2)\xc,xy(2)\yc,xy(3)\xc,xy(3)\yc,cl
  Line xy(3)\xc,xy(3)\yc,xy(0)\xc,xy(0)\yc,cl

; Top

  Line xy(4)\xc,xy(4)\yc,xy(5)\xc,xy(5)\yc,cl
  Line xy(5)\xc,xy(5)\yc,xy(6)\xc,xy(6)\yc,cl
  Line xy(6)\xc,xy(6)\yc,xy(7)\xc,xy(7)\yc,cl
  Line xy(7)\xc,xy(7)\yc,xy(4)\xc,xy(4)\yc,cl

; Lines

  Line xy(0)\xc,xy(0)\yc,xy(4)\xc,xy(4)\yc,cl
  Line xy(1)\xc,xy(1)\yc,xy(5)\xc,xy(5)\yc,cl
  Line xy(2)\xc,xy(2)\yc,xy(6)\xc,xy(6)\yc,cl
  Line xy(3)\xc,xy(3)\yc,xy(7)\xc,xy(7)\yc,cl

Return


; Checks the Mouse Button on Check Boxes
.CheckBoxes

  Use BitMap 2

; Checks Axis of Rotation

  If RectsHit (MouseX,MouseY,1,1,8,8,6,6)  ; YY Axis
    Select yyy
      Case 0
        Line 9,9,13,13,1
        Line 9,13,13,9,1
        yyy=1
      Case 1
        Line 9,9,13,13,0
        Line 9,13,13,9,0
        yyy=0
      End Select
  End If

  If RectsHit (MouseX,MouseY,1,1,40,8,6,6) ; XX Axis
     Select xxx
      Case 0
        Line 41,9,45,13,1
        Line 41,13,45,9,1
        xxx=1
      Case 1
        Line 41,9,45,13,0
        Line 41,13,45,9,0
        xxx=0
      End Select
  End If

  If RectsHit (MouseX,MouseY,1,1,72,8,6,6) ; ZZ Axis
     Select zzz
      Case 0
        Line 73,9,77,13,1
        Line 73,13,77,9,1
        zzz=1
      Case 1
        Line 73,9,77,13,0
        Line 73,13,77,9,0
        zzz=0
      End Select
  End If


; Checks Position of our Object in Virtual World

  If RectsHit (MouseX,MouseY,1,1,168,8,6,6) ; - YY
    YFIXFACTOR-25
  End If

  If RectsHit (MouseX,MouseY,1,1,192,8,6,6) ; + YY
    YFIXFACTOR+25
  End If

  If RectsHit (MouseX,MouseY,1,1,208,8,6,6) ; - XX
    XFIXFACTOR-25
  End If

  If RectsHit (MouseX,MouseY,1,1,232,8,6,6) ; + XX
    XFIXFACTOR+25
  End If

  If RectsHit (MouseX,MouseY,1,1,248,8,6,6) ; - ZZ
    ZFIXFACTOR-25
  End If

  If RectsHit (MouseX,MouseY,1,1,272,8,6,6) ; + ZZ
    ZFIXFACTOR+25
  End If


; Checks NF / Scalling

  If RectsHit (MouseX,MouseY,1,1,8,32,6,6)   ; - NF
    NF-10
  End If

  If RectsHit (MouseX,MouseY,1,1,32,32,6,6)  ; + NF
    NF+10
  End If

  If RectsHit (MouseX,MouseY,1,1,48,32,6,6)  ; - Scalling
    scalling-10
  End If

  If RectsHit (MouseX,MouseY,1,1,120,32,6,6) ; + Scalling
    scalling+10
  End If


; Checks Virtual World Coordinates

  If RectsHit (MouseX,MouseY,1,1,168,32,6,6) ; - YY World
    wy-25
  End If

  If RectsHit (MouseX,MouseY,1,1,192,32,6,6) ; + YY World
    wy+25
  End If

  If RectsHit (MouseX,MouseY,1,1,208,32,6,6) ; - XX World
    wx-25
  End If

  If RectsHit (MouseX,MouseY,1,1,232,32,6,6) ; + XX World
    wx+25
  End If

  If RectsHit (MouseX,MouseY,1,1,248,32,6,6) ; - ZZ World
    wz-25
  End If

  If RectsHit (MouseX,MouseY,1,1,272,32,6,6) ; + ZZ World
    wz+25
  End If


; Checks Exit Button

  If RectsHit (MouseX,MouseY,1,1,310,3,6,6) exit=1

Return


; Draws the panel
.DrawPanel

  Use BitMap 2

; Axis Rotation
  Box 0,3,140,19,2
  Locate 1,0
  Colour 3
  Print "Rotation"
  Locate 1,1
  Colour 1
  Print " YY  XX  ZZ"
  Box 8,8,14,14,4
  Box 40,8,46,14,4
  Box 72,8,78,14,4
  Line 9,9,13,13,1
  Line 13,9,9,13,1

; Translation - Position of our object in Virtual World
  Box 160,3,300,19,2
  Locate 21,0
  Colour 3
  Print "Translation"
  Locate 21,1
  Colour 1
  Print "-YY+ -XX+ -ZZ+"
  Box 168,8,174,14,4
  Box 192,8,198,14,4
  Box 208,8,214,14,4
  Box 232,8,238,14,4
  Box 248,8,254,14,4
  Box 272,8,278,14,4

; NF / Scalling
  Box 0,26,140,39,2
  Locate 1,3
  Colour 3
  Print "NF / Scalling"
  Locate 1,4
  Colour 1
  Print "-NF+ -Scalling+"
  Box 8,32,14,38,4
  Box 32,32,38,38,4
  Box 48,32,54,38,4
  Box 120,32,126,38,4

; Virtual World Coordinates
  Box 160,26,300,39,2
  Locate 21,3
  Colour 3
  Print "World"
  Locate 21,4
  Colour 1
  Print "-YY+ -XX+ -ZZ+"
  Box 168,32,174,38,4
  Box 192,32,198,38,4
  Box 208,32,214,38,4
  Box 232,32,238,38,4
  Box 248,32,254,38,4
  Box 272,32,278,38,4

; Exit Button
  Box 310,3,316,9,1
  Line 311,4,315,8,1
  Line 315,4,311,8,1

Return
