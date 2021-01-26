using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class GameManager : MonoBehaviour {

	public static GameManager instance = null;
	public BoardManager boardScript;
	public int playerHealth = 70;
	public int playerCrystals =0;
	public float turnDelay = .1f;
	public float levelStartDelay = 5f;

	[HideInInspector]
	public bool playersTurn = true;

	public int level = 1;
	private bool doingSetup;
	private Text Level5Text;
	private GameObject level1Image;
	private GameObject level2Image;
	private GameObject level3Image;
	private GameObject level4Image;
	private GameObject level5Image;
	private GameObject winImage;
	private GameObject ship;
	private Text Cristals;
	private Text Health;
	private Text Win;
    public bool Gameoverbool = false;
    //public AudioClip musicFinal;
    //public AudioClip musicBeginning;

	// Use this for initialization
	void Awake () {
		if (instance == null){
			instance = this;
		} else if (instance != this) {
			Destroy(gameObject);
		}

		DontDestroyOnLoad(gameObject);
		boardScript = GetComponent<BoardManager>();

		InitGame(level);
	}

	private void OnLevelWasLoaded(int index)
	{
        if (Gameoverbool == true)
        {
            playerHealth = 70;
            playerCrystals = 0;
            Gameoverbool = false;
            level = 1;
            InitGame(level);
        }
        else {
			if (level<4){
            level++;
            InitGame(level);
				Cristals = GameObject.FindWithTag("cristalt").GetComponent(typeof(Text)) as Text;
				Health = GameObject.FindWithTag("healtht").GetComponent(typeof(Text)) as Text;
				Health.text = playerHealth.ToString();
				Cristals.text = playerCrystals.ToString();

			}else{

				playerHealth = 70;
				playerCrystals = 0;
				level = 1;
				InitGame(level);
			}
        }
       
	}

	private void HideLevel1Image() {        
		level1Image.SetActive(false);
		doingSetup = false;
		GameObject playerinstance = GameObject.Find("Player");
		playerinstance.GetComponent<Animator>().SetBool("teleport",false);
	}

	private void HideLevel2Image() {        
		level2Image.SetActive(false);
		doingSetup = false;
		GameObject playerinstance = GameObject.Find("Player");

		playerinstance.GetComponent<Animator>().SetBool("teleport",false);

	}

	private void HideLevel3Image() {        
		level3Image.SetActive(false);
		doingSetup = false;
		GameObject playerinstance = GameObject.Find("Player");
		playerinstance.GetComponent<Animator>().SetBool("teleport",false);
	}

	private void HideLevel4Image() {        
		level4Image.SetActive(false);
		doingSetup = false;
		GameObject playerinstance = GameObject.Find("Player");
		playerinstance.GetComponent<Animator>().SetBool("teleport",false);
	}


	void InitGame(int level) {
		
		doingSetup = true;

		level1Image = GameObject.Find("Level1Image");
		level2Image = GameObject.Find("Level2Image");
		level3Image = GameObject.Find("Level3Image");
		level4Image = GameObject.Find("Level4Image");
		level5Image = GameObject.Find("DeathImage");
		winImage = GameObject.Find("WinImage");
		ship = GameObject.Find("Spaceship");

		level1Image.SetActive(false);
		level2Image.SetActive(false);   
		level3Image.SetActive(false);   
		level4Image.SetActive(false);   
		level5Image.SetActive(false);
		winImage.SetActive(false);  

		if (level==1){

			level1Image.SetActive(true);        
			Invoke("HideLevel1Image", levelStartDelay);
			//SoundManager.instance.RandomizeSfx(musicBeginning);
			//GetComponent<Animator>().SetBool("teleport",true);
		}else if(level==2){  
			ship.SetActive(false); 
			level2Image.SetActive(true);        
			Invoke("HideLevel2Image", levelStartDelay);
			//GetComponent<Animator>().SetBool("teleport",true);
		}else if(level==3){
			ship.SetActive(false); 
			level3Image.SetActive(true);        
			Invoke("HideLevel3Image", levelStartDelay);
			//GetComponent<Animator>().SetBool("teleport",true);
		}else if(level==4){
			ship.SetActive(false);
			level4Image.SetActive(true);        
			Invoke("HideLevel4Image", levelStartDelay);
			SoundManager.instance.musicSource.Stop();
			SoundManager.instance.musicFinal.Play();
			//GetComponent<Animator>().SetBool("teleport",true);
		}



		boardScript.BoardSetup(level);


	}

	public void GameOver() {

	    if(level == 4){
	        SoundManager.instance.musicFinal.Stop();
	    }
		level5Image.SetActive(true);
		Level5Text = GameObject.FindWithTag("gameovertext").GetComponent(typeof(Text)) as Text;

		Level5Text.text = "Has acabado con: "+playerCrystals +" cristales en el nivel " + level;
        Gameoverbool = true;
		enabled = false;

	}

	public void GameWin() {
	
	    SoundManager.instance.musicFinal.Stop();
	    SoundManager.instance.musicWin.Play();
		winImage.SetActive(true);
		Win = GameObject.FindWithTag("wintext").GetComponent(typeof(Text)) as Text;
		Win.text= "Has salvado la Tierra con " +playerCrystals.ToString() + " cristales, ahora puedes usarlos para comprar objetos nuevos";
		enabled = false;
	}


	// Update is called once per frame
	void Update () {
		if(playersTurn || doingSetup) {
			return;
		}
	}

}