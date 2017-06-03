## Queues 

ArrayQueue:
* The implementation got a bit confusing, mainly because I was trying 
to save a few bytes here and there. The take-home: you can optimize for bytes 
til the cows come home, but if it doubles your implementation time, 
those were some expensive 8-byte savings. Just add a boolean or an 
extra index - if you need to keep track of the size, use n or size. 
Especially with floating head and tail pointers - don't try and do 
fancy math until you have to.


