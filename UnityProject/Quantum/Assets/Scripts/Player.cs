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
	private Text Time;
	public float speed=3;
	private int Crystals;
	private int Healthnum;
	private float Times;
	Rigidbody2D rb;
	public AudioClip pillEfx;
	public AudioClip crystalEfx;
	public AudioClip damageEfx;
	public AudioClip gameOverEfx;
	public AudioClip exitEfx;
	public AudioClip paperEfx;
	public AudioClip musicBeginning;
	private GameObject pistaImage;
	public int lvl;



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

    public void AllFalse()
    {
        Upfalse();
        Downfalse();
        Izqfalse();
        Derfalse();
    }

    public void SomthingTrue()
    {
        if (Izqbool || Derbool || Upbool || Downbool)
        {
            pistaImage.SetActive(false);
        }
    }

	// Use this for initialization
	protected void Start()
	{
		rb= GetComponent<Rigidbody2D>();

		Crystals = GameManager.instance.playerCrystals;
		Healthnum= GameManager.instance.playerHealth;
		Times = GameManager.instance.availableTime;
		Cristals = GameObject.FindWithTag("cristalt").GetComponent(typeof(Text)) as Text;
		Health = GameObject.FindWithTag("healtht").GetComponent(typeof(Text)) as Text;
		Pista = GameObject.FindWithTag("pistat").GetComponent(typeof(Text)) as Text;
		Time = GameObject.FindWithTag("timett").GetComponent(typeof(Text)) as Text;
		pistaImage = GameObject.Find("PistaImage");
		pistaImage.SetActive(false);
		lvl = GameManager.instance.level;


	}

	private void OnDisable()
	{
		GameManager.instance.playerHealth = Healthnum;
		GameManager.instance.playerCrystals = Crystals;
		GameManager.instance.availableTime = Times;
	}

	void OnTriggerEnter2D(Collider2D other)
	{
		if(other.tag == "Exit") {
			lvl = GameManager.instance.level;
			if (lvl==4){
				GameManager.instance.GameWin();
				//SoundManager efecto Win
			}else{
				GetComponent<Animator>().SetBool("teleport",true);
				Invoke("Restart", restartLevelDelay);
				SoundManager.instance.RandomizeSfx(exitEfx);
			}



		} else if(other.tag == "Pills") {
            if (Healthnum < 70)
            {
                if (Healthnum >= 50)
                {

                    Healthnum = 70;
                    Health.text = Healthnum.ToString();
                    other.gameObject.SetActive(false);
                }
                else {
                    Healthnum += pointsPills;
                    Health.text = Healthnum.ToString();
                    
                    other.gameObject.SetActive(false);
                }
				SoundManager.instance.RandomizeSfx(pillEfx);
                
            }


			
		} else if(other.tag == "Cristals") {
			Crystals += pointsCrystals;
			GameManager.instance.playerCrystals = Crystals;
			Cristals.text = Crystals.ToString();
			SoundManager.instance.RandomizeSfx(crystalEfx);
			other.gameObject.SetActive(false);


		} else if (other.tag =="Damage"){
			
			Healthnum -= pointsDamage;
			Health.text = Healthnum.ToString();
			SoundManager.instance.RandomizeSfx(damageEfx);
			CheckIfGameOver();
		} else if (other.tag =="Pista"){
		    lvl = GameManager.instance.level;


		    pistaImage.SetActive(true);
		    if (lvl == 1){
		        Pista.text = "Bienvenido agente Cooper!\n Su nave se ha estrellado y para reparala debes recoger todos los\n minerales que puedas y encontrar la salida de cada planeta.\n Cuidado! El terreno es hostil.\n A la aventura! ";
		    }
		    else if (lvl == 2){
                Pista.text = "Has logrado superar el primer nivel!\n No olvides recoger minerales y evitar aquellas partes del suelo\n que parecen distintas...\n En la tierra Murph sigue trabajando en una solución,\n pero parece que estás solo en esto.\n Suerte crack! ";
            }
            else if (lvl == 3){
                Pista.text = "Te estás acercando, este nivel está más caliente\n que los empastes de un dragón!\n Sigue así y encontrarás la salida, pantera.\n En este tiempo que llevas fuera, tu Betis ha ganado dos Champions!";

            }
            else if (lvl == 4){
                Pista.text = "Ya casi lo tienes!\n Este parece ser un nivel interdimensional,\n que las luces de neón no supongan un problema.\n Que la suerte esté de tu lado, en la tierra esperan tu regreso. ";
            }
		    SoundManager.instance.RandomizeSfx(paperEfx);
		    AllFalse();



		}

	}


	// Update is called once per frame
	void Update()
	{
	    SomthingTrue();
		CheckIfGameOverTime();

		if (Input.GetKey(KeyCode.D) || Derbool){
			if (GetComponent<SpriteRenderer>().flipX==true){
				GetComponent<SpriteRenderer>().flipX=false;
			}
				GetComponent<Animator>().SetBool("Walk",true);
			rb.velocity=new Vector2(speed,0);


		}
		else if (Input.GetKey(KeyCode.A) || Izqbool){
			if (GetComponent<SpriteRenderer>().flipX==false){
				GetComponent<SpriteRenderer>().flipX=true;
			}
			GetComponent<Animator>().SetBool("Walk",true);
			rb.velocity=new Vector2(-speed,0);
		}
		else if (Input.GetKey(KeyCode.W) || Upbool){
			if (GetComponent<SpriteRenderer>().flipY==true){
				GetComponent<SpriteRenderer>().flipY=false;
			}
			GetComponent<Animator>().SetBool("Walk",true);
			rb.velocity=new Vector2(0,speed);

		}
		else if (Input.GetKey(KeyCode.S) || Downbool){

			GetComponent<Animator>().SetBool("Walk",true);
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
		if(Healthnum <= 0 ) {
		    SoundManager.instance.RandomizeSfx(gameOverEfx);
			SoundManager.instance.musicSource.Stop();
			GameManager.instance.GameOver();
		}
	}

	private void CheckIfGameOverTime()
	{
		if(Times <= 0 ) {
			SoundManager.instance.RandomizeSfx(gameOverEfx);
			SoundManager.instance.musicSource.Stop();
			GameManager.instance.GameOver();
		}
	}


	private void Restart() {
		//Application.LoadLevel(Application.loadedLevel);
		SceneManager.LoadScene(SceneManager.GetActiveScene().name);
	}
}