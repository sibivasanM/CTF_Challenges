import base64

def xor_bytes(a: bytes, b: bytes) -> bytes:
    return bytes([x ^ y for x, y in zip(a, b)])

def manual_gcm_static_iv_attack():
    print("\n--- AES-GCM Static IV XOR Attack Tool ---\n")

    known_plain = input("[1] Enter known plaintext: ").encode()
    c1_b64 = input("[2] Enter ciphertext of known plaintext (Base64): ")
    c2_b64 = input("[3] Enter target ciphertext to decrypt (Base64): ")

    try:
        c1 = base64.b64decode(c1_b64)
        c2 = base64.b64decode(c2_b64)
    except Exception as e:
        print("[!] Base64 decode error:", str(e))
        return

    if len(c1) < len(known_plain) or len(c2) < len(known_plain):
        print("[!] Error: ciphertexts are too short for this known plaintext.")
        return

    c1_short = c1[:len(known_plain)]
    c2_short = c2[:len(known_plain)]

    keystream = xor_bytes(known_plain, c1_short)
    recovered = xor_bytes(c2_short, keystream)

    print("\n[+] Recovered plaintext:", recovered.decode(errors='ignore'))

if __name__ == "__main__":
    manual_gcm_static_iv_attack()
