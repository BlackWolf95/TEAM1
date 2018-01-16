let x = 
   let y = 1 + 2 in y
in let rec succ x = x + 1 
in let rec double x = 2 * x 
in print_int (succ (double x))


let rec add x y =x+y
in print_int(add 3 4)


let x = let y = let z = 1 + 2 in z in y
in x


let(x,y)= 
  (3,4) in x+y in
(print_int (x+y))

//LET
let(x,y)= 
  (3,4) in
let z = x + y in
print_int z

let(x,y)= 
  (3,4) in 
  let(x,y)= 
  (3,4) in
let z = x + y in
print_int z


//LET TUPLE
let(x,y)= 
  (3,4) in 
 let (m,n) = (5,6) in 
 print_int(5,6)

print_int (let (m,x) = m+n in print_int(5,6))


//LET REC
let(x,y)= (3,4)
   in
print_int (let rec m n = m+n in print_int (ack 5,6))

let(x,y)= (3,4)
   in
let rec adder z = x + y in adder 
   in
print_int z



let(x,y)= 
  (3,4) in let (x,y) = (3,4)
in 
let z = x + y in
print_int z




