using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class Player : MonoBehaviour
{

	public int pointsCrystals = 10;
	public int pointsPills = 20;
	public int pointsDamage = 5;
	public float restartLevelDelay = 1f;
	private Text Cristals;
	private Text Health;
	private Text Pista;
	public float speed=3;
	private int Crystals;
	private int Healthnum;
	Rigidbody2D rb;

    //public Text CrystalsText;
    //public Text HealthText;


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
	protected void Start()
	{
		rb= GetComponent<Rigidbody2D>();

		Crystals = GameManager.instance.playerCrystals;
		Healthnum= GameManager.instance.playerHealth;
		Cristals = GameObject.FindWithTag("cristalt").GetComponent(typeof(Text)) as Text;
		Health = GameObject.FindWithTag("healtht").GetComponent(typeof(Text)) as Text;
		Pista = GameObject.FindWithTag("pistat").GetComponent(typeof(Text)) as Text;
	}

	private void OnDisable()
	{
		GameManager.instance.playerHealth = Healthnum;
		GameManager.instance.playerCrystals = Crystals;
	}

	void OnTriggerEnter2D(Collider2D other)
	{
		if(other.tag == "Exit") {
			Invoke("Restart", restartLevelDelay);
		} else if(other.tag == "Pills") {
				Healthnum += pointsPills;
				Health.text = Healthnum.ToString();

			//SoundManager.instance.RandomizeSfx(eatSound1, eatSound2);
			other.gameObject.SetActive(false);
		} else if(other.tag == "Cristals") {
			Crystals += pointsCrystals;
			Cristals.text = Crystals.ToString();
			//SoundManager.instance.RandomizeSfx(drinkSound2, drinkSound2);
			other.gameObject.SetActive(false);

		} else if (other.tag =="Damage"){
			Healthnum -= pointsDamage;
			Health.text = Healthnum.ToString();
			//SoundManager.instance.RandomizeSfx(eatSound1, eatSound2);
		}
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

	private void CheckIfGameOver()
	{
		if(Healthnum <= 0) {
			//SoundManager.instance.PlaySingle(gameOverSound);
			//SoundManager.instance.musicSource.Stop();
			GameManager.instance.GameOver();
		}
	}



	private void Restart() {
		//Application.LoadLevel(Application.loadedLevel);
		SceneManager.LoadScene(SceneManager.GetActiveScene().name);
	}
}