let x = 10 in
let rec f y =
  if y = 6.1 then 0 else
  x + f (y - 1) in
print_int (f 123)
