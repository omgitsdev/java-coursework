'''rational.py:  Module to do rational arithmetic.'''
import math
class Rational(object):
    """An instance represents a rational number.
    """
    def __init__ ( self, num, den ):
        """Constructor for Rational.
        """
        if den == 0:
            raise ValueError, 'denominator cannot be 0'
        else:          
            self.num  =  num
            self.den  =  den
        
    def add( self, other ):
        """Add two rational numbers.
        """
        return Rational ( self.num * other.den + other.num * self.den,
                          self.den * other.den )

    def sub ( self, other ):
        """Return self minus other.
        """
        return Rational ( self.num * other.den - other.num * self.den,
                          self.den * other.den )
        

    def mul( self, other ):
        """Implement multiplication.
        """
        return  Rational ( self.num * other.num, self.den * other.den )

    def div ( self, other ):
        """Implement division.
        """
        return  Rational ( self.num * other.den, self.den * other.num )

    def sqr(self):
        ''' Implement squaring of the rational '''
        return self.mul(self)
    
    def reduceToLowestForm(self):
        """Cancel out common factors
           between numerator and denominator
        """
        maxPossibleFactor = min(self.num, self.den);
        for i in range(2, maxPossibleFactor + 1):
            if (self.num%i==0) and (self.den%i==0):
                self.num = self.num/i
                self.den = self.den/i
        
        
    def __str__ ( self ):
        '''Display self as a string.
        '''
        if self.den != 1:
            return  str(self.num) + "/" + str(self.den)
        else:
            return str(self.num)
    
    def decimal( self ):
        """Implement the float() conversion function.
        """
        return  float ( self.n ) / float ( self.d )

def main():
    r = Rational(1,4)
    r1 = Rational(1,4)
    r2 = r.add(r1)
    r2.reduceToLowestForm()
    print str(r1) + ' added to ' + str(r) + ' = ' + str(r2)
    r3 = Rational(2,3)
    r4 = Rational(3,2)
    r5 = r3.mul(r4)
    r5.reduceToLowestForm()
    print str(r5)




