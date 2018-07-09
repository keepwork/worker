
    (function() {   
        //   
        // 密文字符集（size:65）。   
        // [0-9A-Za-z$_~]   
        //   
        var _hexCHS = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz$_~';   
      
        //   
        // Base64 变形加密法   
        // 算法与 Base64 类似，即将 8 位字节用 6 位表示。   
        // 规则：   
        // 1. 码值 <= 0xff 的用 1 个字节表示；   
        // 2. 码值 > 0xff 的用 2 字节表示；   
        // 3. 单/双字节序列间用 0x1d 进行分隔；   
        // 4. 首字为双字节时即前置 0x1d 分隔符。   
        //   
        // @param array key  - [0-63] 互斥值数组，length == 64   
        //    
        Hex64 = function( key )   
        {   
            this._key = [], this._tbl = {};   
      
            for (var _i=0; _i<64; ++_i) {   
                this._key[_i] = _hexCHS.charAt(key[_i]);   
                this._tbl[this._key[_i]] = _i;   
            }   
      
            this._pad = _hexCHS.charAt(64);   
        };   
      
        // 加密   
        Hex64.prototype.enc = function( s )   
        {   
            var _rs = '';   
            var _c1, _c2, _c3, _n1, _n2, _n3, _n4;   
            var _i = 0;   
            var _a = Hex64._2to1(s);   
            var _en = _a.length % 3, _sz = _a.length - _en;   
            while (_i < _sz) {   
                _c1 = _a[_i++];   
                _c2 = _a[_i++];   
                _c3 = _a[_i++];   
                _n1 = _c1 >> 2;   
                _n2 = ((_c1 & 3) << 4) | (_c2 >> 4);   
                _n3 = ((_c2 & 15) << 2) | (_c3 >> 6);   
                _n4 = _c3 & 63;   
                _rs += this._key[_n1]   
                    + this._key[_n2]   
                    + this._key[_n3]   
                    + this._key[_n4];   
            }   
            if (_en > 0) {   
                _c1 = _a[_i++];   
                _c2 = _en > 1 ? _a[_i] : 0;   
                _n1 = _c1 >> 2;   
                _n2 = ((_c1 & 3) << 4) | (_c2 >> 4);   
                _n3 = (_c2 & 15) << 2;   
                _rs += this._key[_n1] + this._key[_n2]   
                    + (_n3 ? this._key[_n3] : this._pad)   
                    + this._pad;   
            }   
            return  _rs.replace(/.{76}/g, function(s) {   
                return  s + '\n';   
            });   
        };   
      
        // 解密   
        Hex64.prototype.dec = function( s )   
        {   
            var _sa = [],   
                _n1, _n2, _n3, _n4,   
                _i = 0, _c = 0;   
            s = s.replace(/[^0-9A-Za-z$_~]/g, '');   
            while (_i < s.length) {   
                _n1 = this._tbl[s.charAt(_i++)];   
                _n2 = this._tbl[s.charAt(_i++)];   
                _n3 = this._tbl[s.charAt(_i++)];   
                _n4 = this._tbl[s.charAt(_i++)];   
                _sa[_c++] = (_n1 << 2) | (_n2 >> 4);   
                _sa[_c++] = ((_n2 & 15) << 4) | (_n3 >> 2);   
                _sa[_c++] = ((_n3 & 3) << 6) | _n4;   
            }   
            var _e2 = s.slice(-2);   
            if (_e2.charAt(0) == this._pad) {   
                _sa.length = _sa.length - 2;   
            } else if (_e2.charAt(1) == this._pad) {   
                _sa.length = _sa.length - 1;   
            }   
            return  Hex64._1to2(_sa);   
        };   
      
        //   
        // 辅助：   
        // Unicode 字符串 -> 单字节码值数组   
        // 注意：   
        // 原串中值为 0x1d 的字节（非字符）会被删除。   
        //   
        // @param string s  - 字符串（UCS-16）   
        // @return array  - 单字节码值数组   
        //   
        Hex64._2to1 = function( s )   
        {   
            var _2b = false, _n = 0, _sa = [];   
      
            if (s.charCodeAt(0) > 0xff) {   
                _2b = true;   
                _sa[_n++] = 0x1d;   
            }   
            for (var _i=0; _i<s.length; ++_i) {   
                var _c = s.charCodeAt(_i);   
                if (_c == 0x1d) continue;   
                if (_c <= 0xff) {   
                    if (_2b) {   
                        _sa[_n++] = 0x1d;   
                        _2b = false;   
                    }   
                    _sa[_n++] = _c;   
                } else {   
                    if (! _2b) {   
                        _sa[_n++] = 0x1d;   
                        _2b = true;   
                    }   
                    _sa[_n++] = _c >> 8;   
                    _sa[_n++] = _c & 0xff;   
                }   
            }   
            return  _sa;   
        };   
      
        //   
        // 辅助：   
        // 单字节码值数组 -> Unicode 字符串   
        //   
        // @param array a  - 单字节码值数组   
        // @return string  - 还原后的字符串（UCS-16）   
        //   
        Hex64._1to2 = function( a )   
        {   
            var _2b = false, _rs = '';   
      
            for (var _i=0; _i<a.length; ++_i) {   
                var _c = a[_i];   
                if (_c == 0x1d) {   
                    _2b = !_2b;   
                    continue;   
                }   
                if (_2b) {   
                    _rs += String.fromCharCode(_c * 256 + a[++_i]);    
                } else {   
                    _rs += String.fromCharCode(_c);   
                }   
            }   
            return  _rs;   
        };   
      
    })();  