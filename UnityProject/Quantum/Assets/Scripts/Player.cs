using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class Player : MonoBehaviour
{

	public float speed=3;
	Rigidbody2D rb;
    
    //definimos los objetos

    public int pointsCrystals = 10;
    public int pointsPills = 20;
    public int pointsDamage = 5;
    //public Text CrystalsText;
    //public Text HealthText;

    private Animator animator;
    private SpriteRenderer spriteRenderer;

    public int Crystals = 0;
    public int Health  = 70;
    public bool Derbool, Izqbool, Upbool, Downbool;

    public void DerTrue()
    {
        Derbool = true;
    }
    public void Derfalse()
    {
        Derbool = false;
    }
    public void IzqTrue()
    {
        Izqbool = true;
    }
    public void Izqfalse()
    {
        Izqbool = false;
    }
    public void UpTrue()
    {
        Upbool = true;
    }
    public void Upfalse()
    {
        Upbool = false;
    }
    public void DownTrue()
    {
        Downbool = true;
    }
    public void Downfalse()
    {
        Downbool = false;
    }
	// Use this for initialization
	private void Start()
	{
		rb= GetComponent<Rigidbody2D>();
        animator = GetComponent<Animator>();
        spriteRenderer = GetComponent<SpriteRenderer>();
        Crystals = GameManager.instance.playerCrystals;
        Health = GameManager.instance.playerHealth;
        //CrystalText.text = "Crystal: " + Crystals;
        //HealthText.text = "Health: " + Health;
	}



	// Update is called once per frame
	void Update()
	{


		if (Input.GetKey(KeyCode.D) || Derbool){
			if (GetComponent<SpriteRenderer>().flipX==true){
				GetComponent<SpriteRenderer>().flipX=false;
			}
				GetComponent<Animator>().SetBool("Walk",true);
				//transform.Translate(0.015f,0,0);
			rb.velocity=new Vector2(speed,0);

		}
		else if (Input.GetKey(KeyCode.A) || Izqbool){
			if (GetComponent<SpriteRenderer>().flipX==false){
				GetComponent<SpriteRenderer>().flipX=true;
			}
			GetComponent<Animator>().SetBool("Walk",true);
			//transform.Translate(-0.015f,0,0);
			rb.velocity=new Vector2(-speed,0);
		}
		else if (Input.GetKey(KeyCode.W) || Upbool){
			if (GetComponent<SpriteRenderer>().flipY==true){
				GetComponent<SpriteRenderer>().flipY=false;
			}
			GetComponent<Animator>().SetBool("Walk",true);
			//transform.Translate(0,0.015f,0);
			rb.velocity=new Vector2(0,speed);
		}
		else if (Input.GetKey(KeyCode.S) || Downbool){

			GetComponent<Animator>().SetBool("Walk",true);
			//transform.Translate(0,-0.015f,0);
			rb.velocity=new Vector2(0,-speed);
		}

        if (Input.GetKeyUp(KeyCode.A) || Input.GetKeyUp(KeyCode.D) || Input.GetKeyUp(KeyCode.S) || Input.GetKeyUp(KeyCode.W) ||(!Downbool && !Izqbool && !Derbool && !Upbool))
        {
			GetComponent<Animator>().SetBool("Walk",false);
			rb.velocity= new Vector2(0,0);

		}

        
	}




}